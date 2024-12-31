package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.library.domain.entities.CharacterEntity;
import com.karmalib.karmalibbackend.library.domain.entities.GenreEntity;
import com.karmalib.karmalibbackend.library.domain.enums.ModerationStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
public class CharacterModel extends BaseModel {
    private String name;
    private FileModel avatar;
    private String description;
    private UUID authorId;
    private String alternativeName;
    private ModerationStatus status;

    public static CharacterModel fromEntity(CharacterEntity entity) {
        return CharacterModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .avatar(entity.getAvatar() != null ? FileModel.fromEntity(entity.getAvatar()) : null)
                .description(entity.getDescription())
                .authorId(entity.getAuthor().getId())
                .alternativeName(entity.getAlternativeName())
                .status(entity.getStatus())
                .build();
    }
}
