package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class AddCommentaryCommand extends BaseCommand {
    @NonNull
    private String text;
    private Boolean spoiler = false;
    private UUID titleId;
    private UUID postId;
    private UUID chapterId;
}
