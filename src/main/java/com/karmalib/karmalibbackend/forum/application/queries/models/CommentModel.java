package com.karmalib.karmalibbackend.forum.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.user.application.queries.results.UserModel;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CommentModel extends BaseModel {
    private UserModel author;
    private String text;
    private Boolean isDeleted;
    private Boolean isSpoiler;

    public static CommentModel fromEntity(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        return CommentModel.builder()
                .id(entity.id) // Поле id из BaseEntity (публичное)
                .createdAt(entity.createdAt) // Поле createdAt из BaseEntity (публичное)
                .updatedAt(entity.updatedAt) // Поле updatedAt из BaseEntity (публичное)
                .author(UserModel.fromEntity(entity.getAuthor())) // Используем геттер для приватного поля
                .text(entity.getText()) // Используем геттер для приватного поля
                .isDeleted(entity.getIsDeleted()) // Используем геттер для приватного поля
                .isSpoiler(entity.getIsSpoiler()) // Используем геттер для приватного поля
                .build();
    }
}