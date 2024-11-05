package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.application.services.PasswordHasherService;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser implements ICommandHandler<CreateUserCommand> {
    final UserRepository userRepository;
    final PasswordHasherService passwordHasherService;

    @Autowired
    public CreateUser(UserRepository userRepo, PasswordHasherService passwordHasherService) {
        this.userRepository = userRepo;
        this.passwordHasherService = passwordHasherService;
    }

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

        return CommandResult.empty();
    }
}
