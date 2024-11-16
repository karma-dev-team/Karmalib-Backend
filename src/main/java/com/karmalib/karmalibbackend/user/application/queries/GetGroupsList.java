package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.GroupModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGroupsList  implements IQueryHandler<GetGroupsListQuery, List<GroupModel>> {
    @Override
    public List<GroupModel> handle(GetGroupsListQuery query) {

        return null;
    }
}