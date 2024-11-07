package com.karmalib.karmalibbackend.user.domain.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendInvitationDeclined {
    private final UUID senderId;
    private final UUID receiverId;

    public FriendInvitationDeclined(UUID senderId, UUID receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
