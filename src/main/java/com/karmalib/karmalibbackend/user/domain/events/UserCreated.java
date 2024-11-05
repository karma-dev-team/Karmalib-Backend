package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreated extends BaseEvent {
    private UserEntity user;
}
