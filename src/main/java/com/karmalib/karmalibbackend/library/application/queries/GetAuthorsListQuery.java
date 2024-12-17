package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAuthorsListQuery extends BaseQuery {
    private Boolean isAllowedToPost;
}