package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class UpdateCommentCommand extends BaseCommand {
    @NonNull
    private UUID commentId;
    private String text;
    private Boolean isSpoiler;
}
