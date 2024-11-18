package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateGroupCommand extends BaseCommand {
    private UUID groupId;                // ID группы для обновления
    private String name;                 // Новое имя группы
    private String description;          // Новое описание группы
    private String shortDescription;     // Новое краткое описание
    private Boolean isBanned;            // Флаг блокировки группы
    private Boolean isPendingDeletion;   // Флаг ожидания удаления
    private Boolean isDeletionRequestedByAdmin; // Флаг удаления админом
}