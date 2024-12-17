package com.karmalib.karmalibbackend.user.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuperBuilder
public class GroupModel extends BaseModel {
    private UUID id;
    private String name;
    private String description;
    private String shortDescription;
    private boolean isPendingDeletion;
    private boolean isDeletionRequestedByAdmin;
    private List<UserModel> users; // Пользователи в группе
    private UserModel owner;       // Владелец группы

    public static GroupModel fromEntity(GroupEntity groupEntity) {
        return GroupModel.builder()
                .id(groupEntity.id)
                .name(groupEntity.getName())
                .description(groupEntity.getDescription())
                .shortDescription(groupEntity.getShortDescription())
                .isPendingDeletion(groupEntity.isPendingDeletion())
                .isDeletionRequestedByAdmin(groupEntity.isDeletionRequestedByAdmin())
                .users(groupEntity.getUsers() == null ? null : groupEntity.getUsers().stream()
                        .map(UserModel::fromEntity)
                        .collect(Collectors.toList()))
                .owner(groupEntity.getOwner() == null ? null : UserModel.fromEntity(groupEntity.getOwner()))
                .createdAt(groupEntity.createdAt)
                .updatedAt(groupEntity.updatedAt)
                .build();
    }
}
