package com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher;

import java.util.List;

public interface IEventDispatcher {
    void dispatch(BaseEvent... events);
    void dispatch(List<BaseEvent> events);
}
