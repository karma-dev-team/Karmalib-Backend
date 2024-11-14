package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import lombok.Data;

import java.util.UUID;

@Data
public class SuspendUserCommand implements ICommand {
    private UUID userId;
    private String reason;
    private boolean suspend = true;
}