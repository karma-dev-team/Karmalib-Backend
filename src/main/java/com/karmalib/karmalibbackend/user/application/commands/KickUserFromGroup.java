package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import org.springframework.stereotype.Service;

@Service
public class KickUserFromGroup implements ICommandHandler<KickUserFromGroupCommand> {
    public CommandResult handle(KickUserFromGroupCommand command) {
        return CommandResult.empty();
    }
}
