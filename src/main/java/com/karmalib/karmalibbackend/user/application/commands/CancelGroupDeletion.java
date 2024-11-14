package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelGroupDeletion implements ICommandHandler<CancelGroupDeletionCommand> {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(CancelGroupDeletionCommand command) {
        GroupEntity group = groupRepository.findById(command.getGroupId()).orElse(null);
        if (group == null) {
            return CommandResult.failure("Group not found");
        }

        // Проверка, что текущий пользователь — владелец группы
        if (!group.getOwner().id.equals(command.getUserId())) {
            return CommandResult.failure("Only the group owner can cancel the deletion");
        }

        // Проверка, что удаление инициировано владельцем, а не админом
        if (group.isDeletionRequestedByAdmin()) {
            return CommandResult.failure("Deletion initiated by admin cannot be canceled");
        }

        // Отмена удаления
        group.setPendingDeletion(false);
        groupRepository.save(group);

        return CommandResult.success(group.id);
    }
}