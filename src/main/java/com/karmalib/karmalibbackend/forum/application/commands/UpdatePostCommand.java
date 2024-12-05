package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.SaveFileCommand;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class UpdatePostCommand extends BaseCommand {
    @NonNull
    private UUID postId;
    private String text;
    private Boolean hidden;
    private List<SaveFileCommand> attachments;
}
