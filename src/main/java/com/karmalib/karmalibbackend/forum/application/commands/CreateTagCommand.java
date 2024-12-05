package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;

@Data
public class CreateTagCommand extends BaseCommand {
    private String name;
    private Boolean hidden = false;
    private String description;
}
