package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendAdded extends BaseEvent {
    private final UUID userId;
    private final UUID friendId;

    public FriendAdded(UUID userId, UUID friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}