package com.karmalib.karmalibbackend.user.application.eventHandlers;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventSubscriber;
import com.karmalib.karmalibbackend.user.domain.events.AccountDeleted;

public class AccountDeletedEventHandler implements IEventSubscriber<AccountDeleted> {
    public void handle(AccountDeleted event) {
    }
}
