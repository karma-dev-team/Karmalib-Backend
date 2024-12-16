package com.karmalib.karmalibbackend.library.application.commands;


import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTitle implements ICommandHandler<UpdateTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(UpdateTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }


        title.setTitle(command.getNewTitle());
        title.setDescription(command.getNewDescription());
        titleRepository.save(title);

        return CommandResult.success(title.getId());
    }
}