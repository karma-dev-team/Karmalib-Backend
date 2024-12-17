package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateTitleCommand extends BaseCommand {
    private String name;
    private String description;
    @Nullable
    private List<String> alternateNames;
    private List<UUID> genreIds;
    private List<UUID> creatorIds;
    private List<UUID> tagIds;

    private TitleStatus status;
    @Nullable
    private PgRatings pgRatings;
    @Nullable
    private Boolean hentai;
    @Nullable
    private Boolean ronabe;
    @Nullable
    private UUID groupId;
    @Nullable
    private UUID userId;
    private LocalDateTime releasedAt;
    private InputFileCommand coverImage;
}