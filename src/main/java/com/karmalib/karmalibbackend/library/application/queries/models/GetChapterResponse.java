package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
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
public class GetChapterResponse extends BaseModel {
    private UUID chapterId;
    private int index;
    private LocalDateTime publicationDate;
    private AuthorModel author;
    private LocalDateTime delayedPublicationDate;
    private List<ChapterSegmentModel> segments;
    private boolean paid;
    private BigDecimal amountToPay;
    private UUID titleId;

    public static GetChapterResponse fromEntity(ChapterEntity chapterEntity, ChapterTranslationEntity currentTranslation) {
        return GetChapterResponse.builder()
                .id(chapterEntity.getId())
                .createdAt(chapterEntity.getCreatedAt())
                .updatedAt(chapterEntity.getUpdatedAt())
                .chapterId(chapterEntity.getId())
                .index(chapterEntity.getIndex())
                .publicationDate(currentTranslation.getPublicationDate())
                .author(currentTranslation.getAuthor() != null
                        ? AuthorModel.fromEntity(currentTranslation.getAuthor())
                        : null)
                .delayedPublicationDate(currentTranslation.getDelayedPublicationDate())
                .segments(currentTranslation.getSegments() != null
                        ? IntStream.range(0, currentTranslation.getSegments().size())
                        .mapToObj(i -> ChapterSegmentModel.fromEntity(currentTranslation.getSegments().get(i), i + 1))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .paid(currentTranslation.isPaid())
                .amountToPay(currentTranslation.getAmountToPay())
                .titleId(chapterEntity.getTitle() != null ? chapterEntity.getTitle().getId() : null)
                .build();
    }
}