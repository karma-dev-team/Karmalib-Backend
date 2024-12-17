package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteRecommendation implements ICommandHandler<DeleteRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;
    

    @Override
    public CommandResult handle(DeleteRecommendationCommand command) {
        var recommendation = recommendationRepository.findById(command.getRecommendationId()).orElse(null);

        if (recommendation == null) {
            return CommandResult.notFound("Recommendation not found", command.getRecommendationId());
        }

        recommendationRepository.delete(recommendation);
        return CommandResult.success(recommendation.id);
    }
}