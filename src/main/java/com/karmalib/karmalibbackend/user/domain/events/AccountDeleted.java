package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;

public class AccountDeleted extends BaseEvent {
    public UserEntity user;

    public AccountDeleted(UserEntity user) {
        super(); // Initializes aggregateId and timestamp from BaseEvent
        this.user = user;
    }
}
