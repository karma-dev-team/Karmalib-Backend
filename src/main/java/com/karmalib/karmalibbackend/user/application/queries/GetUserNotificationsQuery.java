package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.QueryWithPagination;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetUserNotificationsQuery extends QueryWithPagination {
    private UUID userId;
}
