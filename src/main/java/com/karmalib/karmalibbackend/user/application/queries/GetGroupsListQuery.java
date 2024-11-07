package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.Query;
import lombok.Data;

import java.util.UUID;

@Data
public class GetGroupsListQuery implements Query {
    private UUID userId;
    private boolean banned;

}
