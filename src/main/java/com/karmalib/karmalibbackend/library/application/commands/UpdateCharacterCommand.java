package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateCharacterCommand extends BaseCommand {
    @NonNull
    private UUID characterId;
    private String newName;
    private String newAlternativeName;
    private String newDescription;
    private InputFileCommand file;
}