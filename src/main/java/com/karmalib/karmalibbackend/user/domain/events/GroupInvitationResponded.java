package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class GroupInvitationResponded extends BaseEvent {
    private final InvitationStatus status;
    private final UUID groupId;
    private final UUID senderId;
    private final UUID receiverId;
}
