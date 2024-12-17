package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.library.domain.enums.CreatorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateCreatorCommand extends BaseCommand {
    private String name;
    private String description;
    private CreatorType type;
    private List<String> aliases;
}