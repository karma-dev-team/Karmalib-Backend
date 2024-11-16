package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import org.springframework.stereotype.Service;

@Service
public class AddFriend implements ICommandHandler<AddFriendCommand> {
    public CommandResult handle(AddFriendCommand command) {
        return CommandResult.empty();
    }
}
