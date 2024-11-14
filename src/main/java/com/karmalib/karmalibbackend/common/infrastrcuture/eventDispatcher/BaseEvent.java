package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class BaseEvent extends ApplicationEvent implements Serializable {
    private final UUID aggregateId;

    // Default constructor to satisfy Lombok in subclasses
    protected BaseEvent() {
        super("default-source"); // Set a default source for ApplicationEvent
        this.aggregateId = UUID.randomUUID();
    }

    // Constructor allowing specific aggregateId and timestamp
    public BaseEvent(Object source, UUID aggregateId, LocalDateTime timestamp) {
        super(source);
        this.aggregateId = aggregateId != null ? aggregateId : UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "timestamp=" + this.getTimestamp() +
                ", aggregateId=" + aggregateId +
                '}';
    }
}
