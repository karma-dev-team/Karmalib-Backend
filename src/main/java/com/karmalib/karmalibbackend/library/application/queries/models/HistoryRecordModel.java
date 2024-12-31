package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.HistoryRecordEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
public class HistoryRecordModel extends BaseModel {

    private UUID userId;
    private TitleModel title;
    private ChapterModel chapter;
    private LocalDateTime viewedAt;

    public static HistoryRecordModel fromEntity(HistoryRecordEntity entity) {
        return HistoryRecordModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .title(entity.getTitle() != null ? TitleModel.fromEntity(entity.getTitle()) : null)
                .chapter(entity.getChapter() != null ? ChapterModel.fromEntity(entity.getChapter()) : null)
                .viewedAt(entity.getViewedAt())
                .build();
    }
}