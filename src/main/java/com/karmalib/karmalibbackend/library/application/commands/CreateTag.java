package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTag implements ICommandHandler<CreateTagCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(CreateTagCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        title.getTags().add(command.getTag());
        titleRepository.save(title);

        return CommandResult.success(title.getId());
    }
}
