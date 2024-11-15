package com.karmalib.karmalibbackend.user.infrastructure;

import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagingQueue {
    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Spring WebSocket отправка

    public void send(NotificationEntity notification) {
        // Если уведомление адресовано пользователю
        if (notification.getRecipient() != null) {
            sendToUser(notification.getRecipient(), notification);
        } else if (notification.getGroup() != null) {
            sendToGroup(notification.getGroup(), notification);
        }
    }

    private void sendToUser(UserEntity user, NotificationEntity notification) {
        // Отправляем уведомление конкретному пользователю
        String destination = "/topic/notifications/user/" + user.id;
        messagingTemplate.convertAndSend(destination, notification);
    }

    private void sendToGroup(GroupEntity group, NotificationEntity notification) {
        for (UserEntity user : group.getUsers()) {
            sendToUser(user, notification);
        }
    }
}