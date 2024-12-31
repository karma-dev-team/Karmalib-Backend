package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.GenreEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class GenreModel extends BaseModel {
    private String name;
    private boolean visible;
    private String description;

    public static GenreModel fromEntity(GenreEntity entity) {
        return GenreModel.builder()
                .id(entity.id)
                .createdAt(entity.createdAt)
                .updatedAt(entity.updatedAt)
                .name(entity.getName())
                .visible(entity.isVisible())
                .description(entity.getDescription())
                .build();
    }
}