package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationsService {
    private final CreateRecommendation createRecommendation;
    private final DeleteRecommendation deleteRecommendation;
    private final ReactToRecommendation reactToRecommendation;

    @Autowired
    public RecommendationsService(
            CreateRecommendation createRecommendation,
            DeleteRecommendation deleteRecommendation,
            ReactToRecommendation reactToRecommendation
    ) {
        this.createRecommendation = createRecommendation;
        this.deleteRecommendation = deleteRecommendation;
        this.reactToRecommendation = reactToRecommendation;
    }

    public CommandResult createRecommendation(CreateRecommendationCommand command) {
        return createRecommendation.handle(command);
    }

    public CommandResult deleteRecommendation(DeleteRecommendationCommand command) {
        return deleteRecommendation.handle(command);
    }

    public CommandResult reactToRecommendation(ReactToRecommendationCommand command) {
        return reactToRecommendation.handle(command);
    }
}
