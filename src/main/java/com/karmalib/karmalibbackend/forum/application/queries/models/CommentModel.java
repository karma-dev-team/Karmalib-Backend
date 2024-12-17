package com.karmalib.karmalibbackend.forum.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.user.application.queries.models.UserModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class CommentModel extends BaseModel {
    private UserModel author;
    private String text;
    private Boolean isDeleted;
    private Boolean isSpoiler;
    private int likes;
    private boolean pinned;
    private UUID parentCommentId; // Для представления родительского комментария, если он есть
    private List<CommentModel> replies; // Список ответов

    public static CommentModel fromEntity(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        return CommentModel.builder()
                .id(entity.id) // Поле id из BaseEntity
                .createdAt(entity.createdAt) // Поле createdAt из BaseEntity
                .updatedAt(entity.updatedAt) // Поле updatedAt из BaseEntity
                .author(UserModel.fromEntity(entity.getAuthor())) // Конвертация UserEntity -> UserModel
                .text(entity.getText())
                .isDeleted(entity.getIsDeleted())
                .isSpoiler(entity.getIsSpoiler())
                .likes(entity.getLikes())
                .pinned(entity.isPinned())
                .parentCommentId(entity.getParentComment() != null ? entity.getParentComment().id : null) // ID родительского комментария
                .replies(entity.getReplies() != null ?
                        entity.getReplies().stream().map(CommentModel::fromEntity).toList() : null) // Конвертация списка ответов
                .build();
    }
}