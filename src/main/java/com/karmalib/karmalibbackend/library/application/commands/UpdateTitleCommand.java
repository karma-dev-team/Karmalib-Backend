package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
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
    private String name;
    private String description;
    private List<String> tags;
    private InputFileCommand coverImage;
    private String alternateNames;
    private PgRatings pgRating;
}