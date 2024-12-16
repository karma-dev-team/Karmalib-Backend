package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.library.domain.enums.CountryType;
import com.karmalib.karmalibbackend.library.domain.enums.CreatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateAuthorCommand extends BaseCommand {
    @NonNull
    private UUID authorId;
    private String name;
    private String description;
    private CountryType country;
    private List<String> aliases;
    private CreatorType type;
    private InputFileCommand logo;
}