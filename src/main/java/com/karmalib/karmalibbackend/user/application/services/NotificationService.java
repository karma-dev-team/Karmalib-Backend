package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.CreateNotification;
import com.karmalib.karmalibbackend.user.application.commands.CreateNotificationCommand;
import com.karmalib.karmalibbackend.user.application.commands.ReadNotifications;
import com.karmalib.karmalibbackend.user.application.commands.ReadNotificationsCommand;
import com.karmalib.karmalibbackend.user.application.queries.GetNotificationSettings;
import com.karmalib.karmalibbackend.user.application.queries.GetNotificationSettingsQuery;
import com.karmalib.karmalibbackend.user.application.queries.GetUserNotifications;
import com.karmalib.karmalibbackend.user.application.queries.GetUserNotificationsQuery;
import com.karmalib.karmalibbackend.user.application.queries.models.NotificationModel;
import com.karmalib.karmalibbackend.user.application.queries.models.NotificationSettingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private ReadNotifications readNotificationsHandler;
    @Autowired
    private CreateNotification createNotificationHandler;
    @Autowired
    private GetUserNotifications getUserNotificationsHandler;
    @Autowired
    private GetNotificationSettings getNotificationSettingsHandler;

    public CommandResult readNotification(ReadNotificationsCommand command) {
        return readNotificationsHandler.handle(command);
    }

    public CommandResult createNotification(CreateNotificationCommand command) {
        return createNotificationHandler.handle(command);
    }

    public List<NotificationModel> getNotifications(GetUserNotificationsQuery query) {
        return getUserNotificationsHandler.handle(query);
    }

    public NotificationSettingsModel getNotificationSettings(GetNotificationSettingsQuery query) {
        return getNotificationSettingsHandler.handle(query);
    }
}
