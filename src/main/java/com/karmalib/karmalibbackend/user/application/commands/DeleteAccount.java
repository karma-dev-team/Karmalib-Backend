package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.events.AccountDeleted;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// it's available only for user iteslf or superadmin
@Service
public class DeleteAccount implements ICommandHandler<DeleteAccountCommand> {
    @Autowired
    private UserRepository userRepository;

    public CommandResult handle(DeleteAccountCommand command) {


        return CommandResult.empty();
    }
}
