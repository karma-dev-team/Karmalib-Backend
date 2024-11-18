package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.CategoryEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CategoryModel extends BaseModel {
    private String name;
    private boolean visible;
    private String description;

    public static CategoryModel fromEntity(CategoryEntity categoryEntity) {

        return CategoryModel.builder()
                .id(categoryEntity.id)                // ID категории
                .name(categoryEntity.getName())            // Название категории
                .visible(categoryEntity.isVisible())       // Видимость категории
                .description(categoryEntity.getDescription()) // Описание категории
                .createdAt(categoryEntity.createdAt)  // Дата создания
                .updatedAt(categoryEntity.updatedAt)  // Дата обновления
                .build();
    }
}
