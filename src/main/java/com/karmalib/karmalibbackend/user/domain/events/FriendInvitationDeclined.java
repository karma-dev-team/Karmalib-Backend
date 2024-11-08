package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendInvitationDeclined extends BaseEvent {
    private final UUID senderId;
    private final UUID receiverId;

    public FriendInvitationDeclined(UUID senderId, UUID receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
