package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

public interface IEventSubscriber<E> {
    void handle(E event);
}