package com.karmalib.karmalibbackend.library.application.commands;


@Service
public class LikeRecommendation implements ICommandHandler<LikeRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ReactionService reactionService;

    @Override
    public CommandResult handle(LikeRecommendationCommand command) {
        var recommendation = recommendationRepository.findById(command.getRecommendationId()).orElse(null);

        if (recommendation == null) {
            return CommandResult.failure("Recommendation not found");
        }

        reactionService.toggleReaction(command.getUserId(), recommendation.getId(), ReactionType.LIKE);
        return CommandResult.success(recommendation.getId());
    }
}