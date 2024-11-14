package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.EmailMessage;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.IMailingQueue;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateEmail implements ICommandHandler<UpdateEmailCommand> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(UpdateEmailCommand command) {
        if (!accessPolicy.isUserSelf(command.getUserId())) {
            return CommandResult.failure("Access denied");
        }

        UserEntity user = userRepository.findById(command.getUserId()).orElse(null);
        if (user == null) {
            return CommandResult.failure("User not found");
        }

        user.setEmail(command.getEmail());
        userRepository.save(user);

        return CommandResult.success(command.getUserId());
    }
}
