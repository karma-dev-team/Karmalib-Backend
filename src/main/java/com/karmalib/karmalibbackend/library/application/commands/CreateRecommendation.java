package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.RecommendationEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.RecommendationRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRecommendation implements ICommandHandler<CreateRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(CreateRecommendationCommand command) {
        var parentTitle = titleRepository.findById(command.getTitleFromToRecommendId()).orElse(null);
        if (parentTitle == null) {
            return CommandResult.notFound("Не найден parent тайтл", command.getRecommendedTitleId());
        }

        var title = titleRepository.findById(command.getRecommendedTitleId()).orElse(null);
        if (title == null) {
            return CommandResult.notFound("Не найден тайтл", command.getRecommendedTitleId());
        }

        var recommendation = RecommendationEntity.builder()
                .creator(accessPolicy.getCurrentUser())
                .title(title)
                .build();

        parentTitle.getRecommendations().add(recommendation);
        titleRepository.save(parentTitle);
        recommendationRepository.save(recommendation);
        return CommandResult.success(recommendation.id);
    }
}