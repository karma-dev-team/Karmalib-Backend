package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateBookmarkCategoryCommand extends BaseCommand {
    @NonNull
    private UUID categoryId;
    private String newName;
    private Boolean isPublic;
    private Boolean isSendNotifications;
}