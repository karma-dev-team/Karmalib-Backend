package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTitle implements ICommandHandler<DeleteTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(DeleteTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Title not found", command.getTitleId());
        }

        titleRepository.delete(title);
        return CommandResult.success(title.id);
    }
}