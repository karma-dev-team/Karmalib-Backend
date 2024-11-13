package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateGroupCommand implements ICommand {
    private String name;
    private String description;
    private String shortDescription;
    private List<UUID> members;
}
