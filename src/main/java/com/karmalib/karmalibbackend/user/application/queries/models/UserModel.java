package com.karmalib.karmalibbackend.user.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.enums.UserRole;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@SuperBuilder
public class UserModel extends BaseModel {
    private String username;
    private String publicUsername;
    private String email;
    private LocalDateTime registrationDate;
    private String description;
    private boolean isStaff;
    private boolean isSuperuser;
    private int sex;
    private boolean isPrivate;
    private boolean needEmail;
    private boolean isNotifyBookmarks;
    private boolean isBanned;
    private boolean isSuspended;
    private boolean isOnline;
    private Set<UserRole> roles;
    private FileModel avatar;

    public static UserModel fromEntity(UserEntity userEntity) {
        return UserModel.builder()
                .id(userEntity.id)
                .createdAt(userEntity.createdAt)
                .updatedAt(userEntity.updatedAt)
                .username(userEntity.getUsername())
                .publicUsername(userEntity.getPublicUsername())
                .email(userEntity.getEmail())
                .registrationDate(userEntity.getRegistrationDate())
                .description(userEntity.getDescription())
                .isStaff(userEntity.isStaff())
                .isSuperuser(userEntity.isSuperuser())
                .sex(userEntity.getSex())
                .isPrivate(userEntity.isPrivate())
                .needEmail(userEntity.isNeedEmail())
                .isNotifyBookmarks(userEntity.isNotifyBookmarks())
                .isBanned(userEntity.isBanned())
                .isSuspended(userEntity.isSuspended())
                .isOnline(userEntity.isOnline())
                .roles(userEntity.getRoles())
                .avatar(userEntity.getAvatar() != null ? FileModel.fromEntity(userEntity.getAvatar()) : null)
                .build();
    }
}
