package com.karmalib.karmalibbackend.library.application.commands;

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