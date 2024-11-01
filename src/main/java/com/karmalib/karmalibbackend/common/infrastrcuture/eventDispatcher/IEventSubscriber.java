package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

public interface IEventSubscriber {
    public void Handle(BaseEvent event);
}