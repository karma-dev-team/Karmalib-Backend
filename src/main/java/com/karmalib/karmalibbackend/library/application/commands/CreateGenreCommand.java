package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateGenreCommand extends BaseCommand {
    private Boolean visible;
    @NonNull
    private String genreName;
    private String description;
}