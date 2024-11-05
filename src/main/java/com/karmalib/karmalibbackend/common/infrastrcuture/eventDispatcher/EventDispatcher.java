package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventDispatcher implements IEventDispatcher {
    private final Map<Class<? extends BaseEvent>, List<IEventSubscriber<? extends BaseEvent>>> subscribers = new HashMap<>();

    @Override
    public void dispatch(BaseEvent event) {
        List<IEventSubscriber<? extends BaseEvent>> eventSubscribers = subscribers.get(event.getClass());
        if (eventSubscribers != null) {
            for (IEventSubscriber<? extends BaseEvent> subscriber : eventSubscribers) {
                // Safe casting since we know the type from registration
                @SuppressWarnings("unchecked")
                IEventSubscriber<BaseEvent> castedSubscriber = (IEventSubscriber<BaseEvent>) subscriber;
                castedSubscriber.handle(event);
            }
        }
    }

    @Override
    public void registerSubscriber(Class<? extends BaseEvent> eventType, IEventSubscriber<? extends BaseEvent> subscriber) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscriber);
    }
}