package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventDispatcher;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddFriend implements ICommandHandler<AddFriendCommand> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IEventDispatcher eventDispatcher;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(AddFriendCommand command) {
        UserEntity user = accessPolicy.getCurrentUser();
        UserEntity friend = userRepository.findById(command.getFriendId()).orElse(null);

        if (user == null || friend == null) {
            return CommandResult.failure("User or friend not found", command.getFriendId());
        }

        // Проверяем, что friendId уже не находится в списке друзей
        if (user.getFriends().contains(friend)) {
            return CommandResult.failure("The specified user is already a friend", command.getFriendId());
        }

        // Добавляем друга в список друзей пользователя и наоборот (двусторонняя связь)
        user.addFriend(friend);
        friend.addFriend(user);

        // Сохраняем изменения в базе данных
        userRepository.save(user);
        userRepository.save(friend);

        eventDispatcher.dispatch(user.getDomainEvents());
        eventDispatcher.dispatch(friend.getDomainEvents());

        return CommandResult.success(command.getFriendId());
    }
}