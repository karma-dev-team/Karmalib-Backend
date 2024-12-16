package com.karmalib.karmalibbackend.library.application.commands;


import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactToRecommendation implements ICommandHandler<ReactToRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(ReactToRecommendationCommand command) {
        var recommendation = recommendationRepository.findById(command.getRecommendationId()).orElse(null);

        if (recommendation == null) {
            return CommandResult.failure("Recommendation not found");
        }

        recommendation.addReaction(accessPolicy.getCurrentUser(), ReactionType.LIKE);
        return CommandResult.success(recommendation.id);
    }
}