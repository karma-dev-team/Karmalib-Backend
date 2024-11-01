package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEvent
{

    public LocalDateTime timestamp;
    public UUID aggregateId;

    public BaseEvent(UUID AggregateId)
    {
        aggregateId = AggregateId;
        timestamp = LocalDateTime.now();
    }
}