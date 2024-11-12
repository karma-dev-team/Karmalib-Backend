package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

public interface IMailingQueue {
    void sendToQueue(EmailMessage emailMessage);
}
