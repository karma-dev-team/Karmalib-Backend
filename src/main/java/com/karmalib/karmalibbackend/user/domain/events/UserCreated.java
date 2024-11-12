package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import lombok.Builder;

@Builder
public class UserCreated extends BaseEvent {
    private UserEntity user;
}
