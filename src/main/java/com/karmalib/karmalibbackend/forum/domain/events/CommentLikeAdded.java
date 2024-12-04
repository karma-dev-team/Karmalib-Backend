package com.karmalib.karmalibbackend.forum.domain.events;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CommentLikeAdded extends BaseEvent {
    private UUID userId;
}
