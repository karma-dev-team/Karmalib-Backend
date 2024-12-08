package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetPostQuery extends BaseQuery {
    private UUID postId;
}
