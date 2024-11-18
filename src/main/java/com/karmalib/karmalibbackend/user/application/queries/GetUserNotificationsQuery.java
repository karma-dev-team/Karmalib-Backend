package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import com.karmalib.karmalibbackend.common.application.QueryWithPagination;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.NotificationRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
public class GetUserNotificationsQuery extends QueryWithPagination {
    private UUID userId;
}
