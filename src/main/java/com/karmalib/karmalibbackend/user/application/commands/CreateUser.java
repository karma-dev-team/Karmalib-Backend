package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventDispatcher;
import com.karmalib.karmalibbackend.user.application.services.PasswordHasherService;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.events.UserCreated;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser implements ICommandHandler<CreateUserCommand> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordHasherService passwordHasherService;
    @Autowired
    private IEventDispatcher eventDispatcher;

    public CommandResult handle(CreateUserCommand command) {
        var oldUser = userRepository.findByEmail(command.getEmail());
        if (oldUser.isPresent()) {
            return CommandResult.failure("This user is already present");
        }
        var hashedPassword = this.passwordHasherService.hashPassword(command.getPassword());

        var newUser = UserEntity.builder()
                .email(command.getEmail())
                .username(command.getUsername())
                .hashedPassword(hashedPassword)
                .build();

        userRepository.save(newUser);

        eventDispatcher.dispatch(UserCreated
                .builder()
                .user(newUser)
                .build()
        );

        return CommandResult.empty();
    }
}
