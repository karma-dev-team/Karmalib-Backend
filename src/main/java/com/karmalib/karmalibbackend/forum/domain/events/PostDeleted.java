package com.karmalib.karmalibbackend.forum.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PostDeleted extends BaseEvent {
    private UUID postId;
    private String reason;
    private UUID postAuthorId;
}
