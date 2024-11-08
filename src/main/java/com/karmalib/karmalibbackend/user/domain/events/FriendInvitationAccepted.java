package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendInvitationAccepted extends BaseEvent {
    private final UUID senderId;
    private final UUID receiverId;

    public FriendInvitationAccepted(UUID senderId, UUID receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
