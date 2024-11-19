package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetConfidentialInfoQuery extends BaseQuery {
    private UUID userId;
}
