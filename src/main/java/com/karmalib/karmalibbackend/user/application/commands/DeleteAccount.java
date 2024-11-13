package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.EmailMessage;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.IMailingQueue;
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
    @Autowired
    private IMailingQueue mailingQueue;
    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(DeleteAccountCommand command) {
        // удаление аккаунта происходит только после отправки эмейла
        if (!accessPolicy.isUserSelf(command.getUserId()) || !accessPolicy.isSuperAdmin()) {
            return CommandResult.failure("Access denied", command.getUserId());
        }

        mailingQueue.sendToQueue(EmailMessage.builder().build());

        return CommandResult.empty();
    }
}
