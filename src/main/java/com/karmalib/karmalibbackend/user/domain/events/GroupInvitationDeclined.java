package com.karmalib.karmalibbackend.user.domain.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GroupInvitationDeclined {
    private final UUID groupId;
    private final UUID senderId;
    private final UUID receiverId;

    public GroupInvitationDeclined(UUID groupId, UUID senderId, UUID receiverId) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
