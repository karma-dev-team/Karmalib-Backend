package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTitle implements ICommandHandler<CreateTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(CreateTitleCommand command) {
        var title = TitleEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .language(command.getLanguage())
                .build();

        titleRepository.save(title);
        return CommandResult.success(title.getId());
    }
}