package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class NotificationRead extends BaseEvent {
    private final UUID notificationId;

    public NotificationRead(UUID notificationId) {
        this.notificationId = notificationId;
    }
}
