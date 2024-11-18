package com.karmalib.karmalibbackend.user.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import com.karmalib.karmalibbackend.user.domain.enums.GroupContactTypes;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GroupIntegrationAdded extends BaseEvent {
    private final UUID groupId;
    private final GroupContactTypes type;
    private final String url;

    public GroupIntegrationAdded(UUID groupId, GroupContactTypes type, String url) {
        this.groupId = groupId;
        this.type = type;
        this.url = url;
    }
}