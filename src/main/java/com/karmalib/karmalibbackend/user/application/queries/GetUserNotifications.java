package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.NotificationModel;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.NotificationRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserNotifications implements IQueryHandler<GetUserNotificationsQuery, List<NotificationModel>> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationModel> handle(GetUserNotificationsQuery query) {
        var user = userRepository.findById(query.getUserId()).orElse(null);
        if (user == null) {
            return null;
        }

        var notifications = notificationRepository.findAllByRecipientId(query.getUserId());
        return notifications.stream()
                .map(NotificationModel::fromEntity)
                .toList();
    }
}
