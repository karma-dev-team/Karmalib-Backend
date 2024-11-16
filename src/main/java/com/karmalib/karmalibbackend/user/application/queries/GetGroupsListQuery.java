package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Data;

import java.util.UUID;

@Data
public class GetGroupsListQuery extends BaseQuery {
    private UUID userId;
    private boolean banned;
}
