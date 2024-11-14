package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuspendUser implements ICommandHandler<SuspendUserCommand> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(SuspendUserCommand command) {
        // Проверка, что пользователь имеет доступ к приостановке учетной записи
        if (!accessPolicy.isAdmin()) {
            return CommandResult.failure("Access denied");
        }

        // Поиск пользователя по ID
        UserEntity user = userRepository.findById(command.getUserId()).orElse(null);
        if (user == null) {
            return CommandResult.failure("User not found");
        }

        // Приостановка пользователя
        user.setSuspended(true);
        userRepository.save(user);

        return CommandResult.success(command.getUserId());
    }
}
