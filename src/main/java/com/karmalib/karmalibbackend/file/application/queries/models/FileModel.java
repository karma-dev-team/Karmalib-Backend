package com.karmalib.karmalibbackend.file.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
public class FileModel extends BaseModel {
    private String name;      // Имя файла
    private int size;         // Размер файла
    private String mimeType;  // MIME-тип файла
    private int width;
    private int height;

    public static FileModel fromEntity(FileEntity fileEntity) {
        return FileModel.builder()
                .id(fileEntity.id)
                .createdAt(fileEntity.createdAt)
                .updatedAt(fileEntity.updatedAt)
                .name(fileEntity.getName())
                .size(fileEntity.getSize())
                .mimeType(fileEntity.getMimeType())
                .width(fileEntity.getWidth())
                .height(fileEntity.getHeight())
                .build();
    }
}