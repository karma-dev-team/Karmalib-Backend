package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import org.springframework.stereotype.Service;

@Service
public class DeleteFriend implements ICommandHandler<DeleteFriendCommand> {
    public CommandResult handle(DeleteFriendCommand command) {
        return CommandResult.empty();
    }
}
