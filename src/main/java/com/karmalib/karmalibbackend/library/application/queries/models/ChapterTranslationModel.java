package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterTranslationEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuperBuilder
@Data
public class ChapterTranslationModel extends BaseModel {
    private UUID chapterId;
    private LocalDateTime publicationDate;
    private AuthorModel author;
    private LocalDateTime delayedPublicationDate;
    private List<ChapterSegmentModel> segments;
    private boolean paid;
    private BigDecimal amountToPay;

    public static ChapterTranslationModel fromEntity(ChapterTranslationEntity entity) {
        return ChapterTranslationModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .chapterId(entity.getChapter() != null ? entity.getChapter().getId() : null)
                .publicationDate(entity.getPublicationDate())
                .author(AuthorModel.fromEntity(entity.getAuthor()))
                .delayedPublicationDate(entity.getDelayedPublicationDate())
                .segments(entity.getSegments() != null
                        ? IntStream.range(0, entity.getSegments().size())
                        .mapToObj(i -> ChapterSegmentModel.fromEntity(entity.getSegments().get(i), i + 1))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .paid(entity.isPaid())
                .amountToPay(entity.getAmountToPay())
                .build();
    }
}