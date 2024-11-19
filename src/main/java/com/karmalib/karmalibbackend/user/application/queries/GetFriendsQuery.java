package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetFriendsQuery extends BaseQuery {
    private UUID userId = null;
    private String name = null;
    private Boolean online = null;
}
