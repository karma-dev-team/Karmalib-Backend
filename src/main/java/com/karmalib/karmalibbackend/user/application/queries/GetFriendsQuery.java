package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetFriendsQuery extends BaseQuery {
    @Nullable
    private UUID userId;
    @Nullable
    private String name;
    @Nullable
    private Boolean online;
}
