package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadNotifications implements ICommandHandler<ReadNotificationsCommand> {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    @Override
    public CommandResult handle(ReadNotificationsCommand command) {
        // Получаем уведомления пользователя, фильтруя по типу, если задан
        List<NotificationEntity> notifications;
        if (command.getType() != null) {
            notifications = notificationRepository.findAllByRecipientIdAndType(command.getUserId(), command.getType());
        } else {
            notifications = notificationRepository.findAllByRecipientId(command.getUserId());
        }

        // Если уведомлений нет
        if (notifications.isEmpty()) {
            return CommandResult.failure("No notifications found for the user", command.getUserId());
        }

        // Помечаем все уведомления как прочитанные
        notifications.forEach(notification -> notification.setRead(true));

        // Сохраняем изменения
        notificationRepository.saveAll(notifications);

        return CommandResult.success(command.getUserId());
    }
}