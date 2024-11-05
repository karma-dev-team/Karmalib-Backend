package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommandHandler;

import java.util.Optional;
import java.util.UUID;

public class CreateUser implements ICommandHandler<CreateUserCommand, UUID> {
    public Optional<UUID> handle(CreateUserCommand command) {
        return Optional.empty();
    }
}
