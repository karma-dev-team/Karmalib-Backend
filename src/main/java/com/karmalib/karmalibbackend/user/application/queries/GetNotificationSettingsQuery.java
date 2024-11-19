package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class GetNotificationSettingsQuery extends BaseQuery {
    private UUID userId;
}
