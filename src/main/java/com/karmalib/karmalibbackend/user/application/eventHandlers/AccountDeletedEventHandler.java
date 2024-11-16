package com.karmalib.karmalibbackend.user.application.eventHandlers;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventSubscriber;
import com.karmalib.karmalibbackend.user.domain.events.AccountDeleted;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountDeletedEventHandler implements IEventSubscriber<AccountDeleted> {
    @EventListener
    public void handle(AccountDeleted event) {
    }
}
