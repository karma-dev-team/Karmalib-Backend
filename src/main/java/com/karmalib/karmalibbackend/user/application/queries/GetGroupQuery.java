package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Data;

import java.util.UUID;

@Data
public class GetGroupQuery extends BaseQuery {
    private UUID groupId;
}
