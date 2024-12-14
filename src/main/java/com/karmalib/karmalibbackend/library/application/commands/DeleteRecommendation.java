package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteRecommendation implements ICommandHandler<DeleteRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public CommandResult handle(DeleteRecommendationCommand command) {
        var recommendation = recommendationRepository.findById(command.getRecommendationId()).orElse(null);

        if (recommendation == null) {
            return CommandResult.failure("Recommendation not found");
        }

        recommendationRepository.delete(recommendation);
        return CommandResult.success(recommendation.getId());
    }
}