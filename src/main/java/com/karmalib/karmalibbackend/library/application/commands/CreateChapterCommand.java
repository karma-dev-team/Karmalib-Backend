package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateChapterCommand extends BaseCommand {
    private int index;
    private String name;
    @Nullable
    private UUID authorId;
    @Nullable
    private UUID groupId;
    private UUID titleId;
    @Nullable
    private LocalDateTime delayedPublicationDate;
    private List<InputFileCommand> segments;
}
