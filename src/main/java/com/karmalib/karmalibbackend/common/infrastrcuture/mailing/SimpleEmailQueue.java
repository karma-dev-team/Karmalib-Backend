package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class SimpleEmailQueue implements IMailingQueue {
    private final Queue<EmailMessage> queue = new ConcurrentLinkedQueue<>();

    @Autowired
    private IMailingService mailingService;

    // Method to push emails to the queue
    public void sendToQueue(EmailMessage emailMessage) {
        queue.offer(emailMessage);
        processQueue();  // Trigger the process each time a new message is added
    }

    // Asynchronous listener method
    @Async
    public void processQueue() {
        EmailMessage emailMessage;
        while ((emailMessage = queue.poll()) != null) {
            // Inject and use IMailingService implementation to send the email
            // (assuming that it's autowired in this class)
            mailingService.sendMail(emailMessage);
        }
    }
}
