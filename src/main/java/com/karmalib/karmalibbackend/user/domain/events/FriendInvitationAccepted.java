package com.karmalib.karmalibbackend.user.domain.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendInvitationAccepted {
    private final UUID senderId;
    private final UUID receiverId;

    public FriendInvitationAccepted(UUID senderId, UUID receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
