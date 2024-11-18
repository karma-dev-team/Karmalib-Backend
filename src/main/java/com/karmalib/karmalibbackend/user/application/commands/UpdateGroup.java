package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateGroup implements ICommandHandler<UpdateGroupCommand> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public CommandResult handle(UpdateGroupCommand command) {
        GroupEntity group = groupRepository.findById(command.getGroupId())
                .orElse(null);
        if (group == null) {
            return CommandResult.failure("No group found", command.getGroupId());
        }

        // Обновление только тех полей, которые переданы (не null)
        if (command.getName() != null) {
            group.setName(command.getName());
        }
        if (command.getDescription() != null) {
            group.setDescription(command.getDescription());
        }
        if (command.getShortDescription() != null) {
            group.setShortDescription(command.getShortDescription());
        }
        if (command.getIsBanned() != null) {
            group.setBanned(command.getIsBanned());
        }
        if (command.getIsPendingDeletion() != null) {
            group.setPendingDeletion(command.getIsPendingDeletion());
        }
        if (command.getIsDeletionRequestedByAdmin() != null) {
            group.setDeletionRequestedByAdmin(command.getIsDeletionRequestedByAdmin());
        }

        // Сохранение обновленной сущности группы
        groupRepository.save(group);

        return CommandResult.success(group.id);
    }
}
