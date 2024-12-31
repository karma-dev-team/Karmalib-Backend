package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkCategoryEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
public class BookmarkCategoryModel extends BaseModel {
    private UUID userId;
    private String name;
    private Boolean isPubliclyVisible;
    private Boolean isSendNotifications;

    public static BookmarkCategoryModel fromEntity(BookmarkCategoryEntity entity) {
        return BookmarkCategoryModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .userId(entity.getUser().getId())
                .name(entity.getName())
                .isPubliclyVisible(entity.getIsPubliclyVisible())
                .isSendNotifications(entity.getIsSendNotifications())
                .build();
    }
}
