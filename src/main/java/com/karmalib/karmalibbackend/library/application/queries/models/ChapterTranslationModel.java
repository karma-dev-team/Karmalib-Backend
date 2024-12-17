package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterTranslationEntity;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
public class ChapterTranslationModel extends BaseModel {
    private UUID chapterId;
    private LocalDateTime publicationDate;
    private UUID authorId;

    public static ChapterTranslationModel fromEntity(ChapterTranslationEntity entity) {
        return ChapterTranslationModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .chapterId(entity.getChapter().getId())
                .publicationDate(entity.getPublicationDate())
                .authorId(entity.getAuthor().getId())
                .build();
    }
}
