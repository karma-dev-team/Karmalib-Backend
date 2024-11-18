package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.NotificationSettingsModel;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetNotificationSettings implements IQueryHandler<GetNotificationSettingsQuery, NotificationSettingsModel> {
    @Autowired
    private UserRepository userRepository;

    public NotificationSettingsModel handle(GetNotificationSettingsQuery query) {
        return null;
    }
}
