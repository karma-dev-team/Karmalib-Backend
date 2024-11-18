package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.NotificationModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserNotifications implements IQueryHandler<GetUserNotificationsQuery, List<NotificationModel>> {
}
