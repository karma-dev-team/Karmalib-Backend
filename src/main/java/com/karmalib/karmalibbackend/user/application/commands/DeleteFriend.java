package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventDispatcher;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteFriend implements ICommandHandler<DeleteFriendCommand> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IEventDispatcher eventDispatcher;

    @Autowired
    private AccessPolicy accessPolicy;

    @Transactional
    @Override
    public CommandResult handle(DeleteFriendCommand command) {
        // Проверяем, что оба пользователя существуют
        UserEntity user = accessPolicy.getCurrentUser();
        UserEntity friend = userRepository.findById(command.getFriendId()).orElse(null);

        if (user == null || friend == null) {
            return CommandResult.failure("User or friend not found", command.getFriendId());
        }

        // Проверяем, что friendId действительно является другом
        if (!user.getFriends().contains(friend)) {
            return CommandResult.failure("The specified user is not a friend", command.getFriendId());
        }

        // Удаляем связь между пользователями
        user.removeFriend(friend);
        friend.removeFriend(friend);

        // Сохраняем изменения в базе данных
        userRepository.save(user);
        userRepository.save(friend);

        eventDispatcher.dispatch(user.getDomainEvents());
        eventDispatcher.dispatch(friend.getDomainEvents());

        return CommandResult.success(command.getFriendId());
    }
}