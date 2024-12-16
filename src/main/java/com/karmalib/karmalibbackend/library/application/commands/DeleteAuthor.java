package com.karmalib.karmalibbackend.library.application.commands;


import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteAuthor implements ICommandHandler<DeleteAuthorCommand> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CommandResult handle(DeleteAuthorCommand command) {
        var author = authorRepository.findById(command.getAuthorId()).orElse(null);

        if (author == null) {
            return CommandResult.failure("Author not found");
        }

        authorRepository.delete(author);
        return CommandResult.success(author.id);
    }
}