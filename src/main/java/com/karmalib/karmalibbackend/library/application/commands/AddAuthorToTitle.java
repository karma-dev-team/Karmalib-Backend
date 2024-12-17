package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAuthorToTitle implements ICommandHandler<AddAuthorToTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CommandResult handle(AddAuthorToTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);
        var author = authorRepository.findById(command.getAuthorId()).orElse(null);

        if (title == null || author == null) {
            return CommandResult.notFound("Тайтл или автор не найден", command.getAuthorId());
        }

        title.getTranslators().add(author);
        titleRepository.save(title);

        return CommandResult.success(title.id);
    }
}