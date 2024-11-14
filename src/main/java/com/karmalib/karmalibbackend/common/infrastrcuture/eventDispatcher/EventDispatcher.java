package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EventDispatcher implements IEventDispatcher {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EventDispatcher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void dispatch(List<BaseEvent> events) {
        for (BaseEvent event : events) {
            eventPublisher.publishEvent(event);
        }
    }

    public void dispatch(BaseEvent... events) {
        dispatch(Arrays.stream(events).toList());
    }
}