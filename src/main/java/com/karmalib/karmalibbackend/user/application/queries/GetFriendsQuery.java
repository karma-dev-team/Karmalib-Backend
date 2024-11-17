package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Data;

import java.util.UUID;

@Data
public class GetFriendsQuery extends BaseQuery {
    private UUID userId = null;
    private String name = null;
    private Boolean online = null;
}
