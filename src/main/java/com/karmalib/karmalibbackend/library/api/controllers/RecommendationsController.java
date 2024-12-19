package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationsController {

    private final RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> createRecommendation(@RequestBody CreateRecommendationCommand command) {
        CommandResult result = recommendationsService.createRecommendation(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{recommendationId}")
    public ResponseEntity<?> deleteRecommendation(@PathVariable UUID recommendationId) {
        DeleteRecommendationCommand command = DeleteRecommendationCommand.builder().recommendationId(recommendationId).build();
        CommandResult result = recommendationsService.deleteRecommendation(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/{recommendationId}/react")
    public ResponseEntity<?> reactToRecommendation(@PathVariable UUID recommendationId, @RequestBody ReactToRecommendationCommand command) {
        command.setRecommendationId(recommendationId);
        CommandResult result = recommendationsService.reactToRecommendation(command);
        return RestService.buildResponse(result);
    }
}
