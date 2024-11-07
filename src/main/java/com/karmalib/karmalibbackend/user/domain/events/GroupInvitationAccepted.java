package com.karmalib.karmalibbackend.user.domain.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GroupInvitationAccepted {
    private final UUID groupId;
    private final UUID senderId;
    private final UUID receiverId;

    public GroupInvitationAccepted(UUID groupId, UUID senderId, UUID receiverId) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
