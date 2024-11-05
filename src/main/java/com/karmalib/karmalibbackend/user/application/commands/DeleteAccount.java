package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.events.AccountDeleted;

public class DeleteAccount implements ICommandHandler<DeleteAccountCommand> {
    public CommandResult handle(DeleteAccountCommand command) {
        return CommandResult.empty();
    }
}
