package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiveLike implements ICommandHandler<GiveLikeCommand> {
    @Autowired
    private ReactionService reactionService;

    @Override
    public CommandResult handle(GiveLikeCommand command) {
        reactionService.toggleReaction(command.getUserId(), command.getEntityId(), command.getReactionType());
        return CommandResult.success(command.getEntityId());
    }
}