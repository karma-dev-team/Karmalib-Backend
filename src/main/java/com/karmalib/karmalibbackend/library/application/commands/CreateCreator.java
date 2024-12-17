package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.domain.entities.CreatorEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCreator implements ICommandHandler<CreateCreatorCommand> {
    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public CommandResult handle(CreateCreatorCommand command) {
        var creator = creatorRepository.findByName(command.getName()).orElse(null);
        if (creator == null) {
            return CommandResult.badRequest("Уже существует такой издатель/автор");
        }

        var newCreator = CreatorEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .build();

        creatorRepository.save(newCreator);
        return CommandResult.success(creator.id);
    }
}
