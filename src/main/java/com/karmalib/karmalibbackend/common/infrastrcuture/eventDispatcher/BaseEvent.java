package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BaseEvent
{
    private LocalDateTime timestamp = LocalDateTime.now();;
    private UUID aggregateId = UUID.randomUUID();
}