package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventDispatcher;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiveOwnershipOfGroup  implements ICommandHandler<GiveOwnershipOfGroupCommand> {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AccessPolicy accessPolicy;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IEventDispatcher eventDispatcher;

    @Transactional
    public CommandResult handle(GiveOwnershipOfGroupCommand command) {
        // Проверка наличия группы
        GroupEntity group = groupRepository.findById(command.getGroupId()).orElse(null);
        if (group == null) {
            return CommandResult.failure("Group not found");
        }

        // Проверка прав: только текущий владелец группы может передать владение
        if (!group.getOwner().id.equals(accessPolicy.getCurrentUser().id)) {
            return CommandResult.failure("Only the current group owner can transfer ownership");
        }

        var user = userRepository.findById(command.getNewOwnerId()).orElse(null);
        if (user == null) {
            return CommandResult.failure("User not found");
        }

        // Передача владения
        group.changeOwnership(user);
        eventDispatcher.dispatch(group.getDomainEvents());
        groupRepository.save(group);

        return CommandResult.success(group.id);
    }
}