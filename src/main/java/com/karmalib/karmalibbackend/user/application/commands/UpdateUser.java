package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.EmailMessage;
import com.karmalib.karmalibbackend.common.infrastrcuture.mailing.IMailingQueue;
import com.karmalib.karmalibbackend.user.application.services.PasswordHasherService;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUser  implements ICommandHandler<UpdateUserCommand> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordHasherService passwordHasherService;
    @Autowired
    private IMailingQueue mailingQueue;

    public CommandResult handle(UpdateUserCommand command) {
        UserEntity user = userRepository.findById(command.getUserId())
                .orElse(null);
        if (user == null) {
            return CommandResult.failure("No user found", command.getUserId());
        }

        // Update only the provided fields (non-null values)
        if (command.getPassword() != null) {
            String hashedPassword = passwordHasherService.hashPassword(command.getPassword());
            user.setHashedPassword(hashedPassword);

            EmailMessage message = EmailMessage
                    .builder()
                    .body("Your password has been changed, time:" + LocalDateTime.now())
                    .recipient(user.getEmail())
                    .subject("Password change in your karmalib account")
                    .build();

            mailingQueue.sendToQueue(message);
        }
        if (command.getUsername() != null) {
            user.setUsername(command.getUsername());
        }
        if (command.getIsPrivate() != null) {
            user.setPrivate(command.getIsPrivate());
        }
        if (command.getIsStuff() != null) {
            user.setStaff(command.getIsStuff());
        }
        if (command.getSex() != null) {
            user.setSex(command.getSex());
        }
        if (command.getIsNotifyBookmarks() != null) {
            user.setNotifyBookmarks(command.getIsNotifyBookmarks());
        }
        if (command.getDescription() != null) {
            user.setDescription(command.getDescription());
        }

        // Save updated user entity
        userRepository.save(user);

        return CommandResult.success(user.id);
    }
}