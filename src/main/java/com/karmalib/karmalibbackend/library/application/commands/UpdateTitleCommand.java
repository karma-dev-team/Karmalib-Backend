package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateTitleCommand extends BaseCommand {
    @NonNull
    private UUID titleId;
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private List<String> tags;
    @Nullable
    private InputFileCommand coverImage;
    @Nullable
    private List<String> alternateNames;
    @Nullable
    private PgRatings pgRating;
}