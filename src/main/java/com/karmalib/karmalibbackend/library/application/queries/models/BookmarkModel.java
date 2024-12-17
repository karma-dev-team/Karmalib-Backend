package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SuperBuilder
public class BookmarkModel extends BaseModel {
    private UUID userId;
    private TitleModel title;
    private Set<BookmarkCategoryModel> category;

    public static BookmarkModel fromEntity(BookmarkEntity entity) {
        return BookmarkModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .userId(entity.getUser().getId())
                .title(TitleModel.fromEntity(entity.getTitle()))
                .category(entity.getCategory() != null
                        ? entity.getCategory().stream()
                        .map(BookmarkCategoryModel::fromEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}