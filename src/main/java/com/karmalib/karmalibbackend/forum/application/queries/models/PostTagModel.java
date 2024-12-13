package com.karmalib.karmalibbackend.forum.application.queries.models;

import com.karmalib.karmalibbackend.forum.domain.entities.PostTagEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostTagType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class PostTagModel {
    private UUID id; // ID of the tag
    private String name; // Name of the tag
    private String slug; // Slug for URLs
    private String description; // Description of the tag
    private PostTagType type; // Enum representing the type of the tag
    private boolean isVisible; // Visibility status of the tag

    public static PostTagModel fromEntity(PostTagEntity entity) {
        return PostTagModel.builder()
                .id(entity.id)

                .name(entity.getName())
                .slug(entity.getSlug())
                .description(entity.getDescription())
                .type(entity.getType())
                .isVisible(entity.isVisible())
                .build();
    }
}