package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.GroupModel;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class GetGroupsList  implements IQueryHandler<GetGroupsListQuery, List<GroupModel>> {
    @Override
    @Cacheable(value = "groups")
    public List<GroupModel> handle(GetGroupsListQuery query) {

        return null;
    }
}