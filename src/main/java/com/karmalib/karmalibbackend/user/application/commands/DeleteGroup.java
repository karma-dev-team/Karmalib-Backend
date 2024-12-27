package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DeleteGroup implements ICommandHandler<DeleteGroupCommand> {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Transactional
    public CommandResult handle(DeleteGroupCommand command) {
        GroupEntity group = groupRepository.findById(command.getGroupId()).orElse(null);
        if (group == null) {
            return CommandResult.failure("Group not found");
        }

        boolean isAdmin = accessPolicy.isAdmin();
        boolean isOwner = group.getOwner().id.equals(command.getUserId());

        if (!isAdmin && !isOwner) {
            return CommandResult.failure("Only the admin or group owner can delete this group");
        }

        if (group.createdAt.isAfter(LocalDateTime.now().minusDays(1))) {
            return CommandResult.failure("Group can only be deleted one day after creation");
        }

        group.setPendingDeletion(true);
        group.setDeletionRequestedByAdmin(isAdmin);
        groupRepository.save(group);

        return CommandResult.success(group.id);
    }
}
