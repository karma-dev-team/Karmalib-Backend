package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPostsListQuery extends BaseQuery {
    private List<String> tags;
    private boolean recentPopular = false;
}
