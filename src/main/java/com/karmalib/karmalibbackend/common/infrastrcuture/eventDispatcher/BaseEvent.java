package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class BaseEvent extends ApplicationEvent implements Serializable {
    private final UUID aggregateId;

    // Default constructor, setting current time and random UUID if no values are provided
    public BaseEvent(Object source) {
        this(source, UUID.randomUUID(), LocalDateTime.now());
    }

    public BaseEvent(Object source, UUID aggregateId, LocalDateTime timestamp) {
        super(source);
        this.aggregateId = aggregateId != null ? aggregateId : UUID.randomUUID();
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "timestamp=" + this.getTimestamp() +
                ", aggregateId=" + aggregateId +
                '}';
    }
}
