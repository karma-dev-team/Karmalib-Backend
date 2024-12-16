package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAuthor implements ICommandHandler<UpdateAuthorCommand> {
    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private SaveFile saveFile;

    @Override
    public CommandResult handle(UpdateAuthorCommand command) {
        var author = creatorRepository.findById(command.getAuthorId()).orElse(null);

        if (author == null) {
            return CommandResult.notFound("Креатор не найден", command.getAuthorId());
        }

        if (!accessPolicy.isUserSelf(author.getUser().id) || !accessPolicy.isAdmin()) {
            return CommandResult.forbidden("Не админ и не овнер");
        }

        if (command.getName() != null)
            author.setName(command.getName());
        if (command.getDescription() != null)
            author.setDescription(command.getDescription());
        if (command.getCountry() != null)
            author.setCountry(command.getCountry());
        if (command.getAliases() != null)
            author.setAliases(command.getAliases());
        if (command.getType() != null)
            author.setType(command.getType());
        if (command.getLogo() != null) {
            var file = saveFile.handleInner(command.getLogo());

            author.setLogo(file);
        }
        author.setName(command.getName());
        creatorRepository.save(author);

        return CommandResult.success(author.id);
    }
}