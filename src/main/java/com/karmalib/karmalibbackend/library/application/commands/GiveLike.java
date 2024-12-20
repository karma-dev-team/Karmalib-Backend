package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiveLike implements ICommandHandler<GiveLikeCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(GiveLikeCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);
        if (title == null) {
            return CommandResult.notFound("Не найден", command.getTitleId());
        }
        var currentUser = accessPolicy.getCurrentUser();

        if (title.hasReaction(currentUser, command.getReactionType())) {
            title.removeReaction(currentUser, command.getReactionType());
        } else {
            title.addReaction(currentUser, command.getReactionType());
        }

        titleRepository.save(title);
        return CommandResult.success(command.getTitleId());
    }
}