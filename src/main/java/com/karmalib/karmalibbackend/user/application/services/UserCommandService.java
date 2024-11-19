package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Reason for segregation is to make sure code is not too long
@Service
public class UserCommandService {
    @Autowired
    private CreateUser createUser;
    @Autowired
    private DeleteAccount deleteAccount;
    @Autowired
    private AddFriend addFriend;
    @Autowired
    private DeleteFriend deleteFriend;
    @Autowired
    private UpdateEmail updateEmail;
    @Autowired
    private UpdateUser updateUser;
    @Autowired
    private SuspendUser suspendUser;
    @Autowired
    private ReadNotifications readNotificationsHandler;

    public CommandResult readNotifications(ReadNotificationsCommand command) {
        return readNotificationsHandler.handle(command);
    }

    public CommandResult register(CreateUserCommand command) {
        return createUser.handle(command);
    }

    public CommandResult suspend(SuspendUserCommand command) {
        return suspendUser.handle(command);
    }

    public CommandResult delete(DeleteAccountCommand command) {
        return deleteAccount.handle(command);
    }

    public CommandResult addFriend(AddFriendCommand command) {
        return addFriend.handle(command);
    }

    public CommandResult deleteFriend(DeleteFriendCommand command) {
        return deleteFriend.handle(command);
    }

    public CommandResult updateEmail(UpdateEmailCommand command) {
        return updateEmail.handle(command);
    }

    public CommandResult updateUser(UpdateUserCommand command) {
        return updateUser.handle(command);
    }
}
