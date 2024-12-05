package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.EventDispatcher;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTag implements ICommandHandler<CreateTagCommand> {
    @Autowired
    private TitleTagRepository titleTagRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(CreateTagCommand command) {
        if (!accessPolicy.isAdmin()) {
            return CommandResult.failure("Access denied");
        }

        TitleTagEntity tag = TitleTagEntity.builder()
                .name(command.getName())
                .hidden(command.getHidden())
                .description(command.getDescription())
                .build();

        titleTagRepository.save(tag);

        return CommandResult.success(tag.id);
    }
}
