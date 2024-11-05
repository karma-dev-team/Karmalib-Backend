package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

public interface IEventDispatcher {
    void dispatch(BaseEvent event);
    void registerSubscriber(Class<? extends BaseEvent> eventType, IEventSubscriber<? extends BaseEvent> subscriber);
}
