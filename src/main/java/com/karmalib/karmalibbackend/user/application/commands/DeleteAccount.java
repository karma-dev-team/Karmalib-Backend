package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.events.AccountDeleted;

import java.util.Optional;
import java.util.UUID;

public class DeleteAccount implements ICommandHandler<DeleteAccountCommand, UUID> {
    public Optional<UUID> handle(DeleteAccountCommand command) {
        AccountDeleted event = new AccountDeleted(new UserEntity());

        return Optional.empty();
    }
}
