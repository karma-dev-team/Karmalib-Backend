package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetCommentsQuery extends BaseQuery {
    private UUID postId;
    private UUID userId;
    private UUID titleId;
}
