package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.EmailMessage;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.IMailingQueue;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

// it's available only for user iteslf or superadmin
@Service
public class DeleteAccount implements ICommandHandler<DeleteAccountCommand> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMailingQueue mailingQueue;
    @Autowired
    private AccessPolicy accessPolicy;
    @Autowired
    private CacheManager cacheManager;

    @Transactional
    public CommandResult handle(DeleteAccountCommand command) {
        // Check access policy
        if (!accessPolicy.isUserSelf(command.getUserId()) && !accessPolicy.isSuperAdmin()) {
            return CommandResult.failure("Access denied", command.getUserId());
        }

        UserEntity user = userRepository.findById(command.getUserId()).orElse(null);
        if (user == null) {
            return CommandResult.failure("User not found", command.getUserId());
        }

        // Generate a token (e.g., a UUID or any other unique identifier)
        String token = UUID.randomUUID().toString();

        // Put the token into the cache
        Cache cache = cacheManager.getCache("deletion_token");
        if (cache != null) {
            cache.put(command.getUserId(), token); // Use the user ID or another unique key to store the token
        }

        // Send the token via email (or return it for testing, etc.)
        EmailMessage email = EmailMessage.builder()
                .recipient(user.getEmail())
                .subject("Удаление аккаунта")  // поменять на домен!!!
                .body("Токен для удаления аккаунта: " + token)
                .build();
        mailingQueue.sendToQueue(email);

        return CommandResult.success(user.id);
    }
}
