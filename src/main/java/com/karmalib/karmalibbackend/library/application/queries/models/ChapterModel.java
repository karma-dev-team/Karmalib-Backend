package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
public class ChapterModel extends BaseModel {
    private int number;
    private String name;
    private TitleModel title;
    private LocalDateTime publicationDate;
    private boolean paid;
    private BigDecimal amountToPay;
    private List<ChapterTranslationModel> translations;
    private List<CommentModel> comments;

    public static ChapterModel fromEntity(ChapterEntity entity) {
        return ChapterModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .number(entity.getNumber())
                .name(entity.getName())
                .title(TitleModel.fromEntity(entity.getTitle()))
                .translations(entity.getTranslations() != null
                        ? entity.getTranslations().stream()
                        .map(ChapterTranslationModel::fromEntity)
                        .toList()
                        : null)
                .comments(entity.getComments() != null
                        ? entity.getComments().stream()
                        .map(CommentModel::fromEntity)
                        .toList()
                        : null)
                .build();
    }
}
