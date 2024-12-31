package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.RecommendationEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
public class RecommendationModel extends BaseModel {
    private UUID authorId;
    private TitleModel title;
    private Boolean active;
    private int rating;

    public static RecommendationModel fromEntity(RecommendationEntity entity) {
        return RecommendationModel.builder()
                .id(entity.id)
                .createdAt(entity.createdAt)
                .updatedAt(entity.updatedAt)
                .authorId(entity.id)
                .title(TitleModel.fromEntity(entity.getTitle()))
                .active(entity.getActive())
                .rating(entity.getRating())
                .build();
    }
}
