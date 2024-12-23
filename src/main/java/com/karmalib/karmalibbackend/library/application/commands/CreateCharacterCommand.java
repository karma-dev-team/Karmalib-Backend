package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCharacterCommand extends BaseCommand {
    private String name;
    private String description;
    private String alternativeName;
    private InputFileCommand avatar;
    private UUID titleId;
}