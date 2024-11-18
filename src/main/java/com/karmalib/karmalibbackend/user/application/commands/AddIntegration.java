package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.GroupIntegrationEntity;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddIntegration implements ICommandHandler<AddIntegrationCommand> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public CommandResult handle(AddIntegrationCommand command) {
        // Ищем группу по ID
        GroupEntity group = groupRepository.findById(command.getGroupId())
                .orElse(null);

        if (group == null) {
            return CommandResult.failure("Group not found", command.getGroupId());
        }

        // Создаём новую интеграцию
        GroupIntegrationEntity integration = GroupIntegrationEntity.builder()
                .type(command.getType())
                .link(command.getLink())
                .build();

        // Добавляем интеграцию к группе
        group.addIntegration(integration);

        // Сохраняем изменения в группе
        groupRepository.save(group);

        return CommandResult.success(group.id);
    }
}