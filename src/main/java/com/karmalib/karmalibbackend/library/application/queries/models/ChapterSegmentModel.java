package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterSegmentModel {
    private int id; // Кастомный ID
    private String link;
    private int height;
    private int width;

    public static ChapterSegmentModel fromEntity(FileEntity entity, int customId) {
        return ChapterSegmentModel.builder()
                .id(customId) // Установить кастомный ID
                .link(entity.getPath()) // Поле `path` содержит ссылку
                .height(entity.getHeight())
                .width(entity.getWidth())
                .build();
    }
}
