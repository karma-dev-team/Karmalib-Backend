package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.user.application.queries.models.GroupModel;
import com.karmalib.karmalibbackend.user.application.queries.models.UserModel;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
public class AuthorModel extends BaseModel {
    private GroupModel group;   // Связанная группа
    private UserModel user;     // Связанный пользователь
    private UUID titleId;   // Связанное произведение

    public static AuthorModel fromEntity(AuthorEntity authorEntity) {
        return AuthorModel.builder()
                .id(authorEntity.id)
                .group(authorEntity.getGroup() == null ? null : GroupModel.fromEntity(authorEntity.getGroup())) // Преобразование GroupEntity
                .user(authorEntity.getUser() == null ? null : UserModel.fromEntity(authorEntity.getUser()))     // Преобразование UserEntity
                .titleId(authorEntity.id) // Преобразование TitleEntity
                .createdAt(authorEntity.createdAt)
                .updatedAt(authorEntity.updatedAt)
                .build();
    }
}