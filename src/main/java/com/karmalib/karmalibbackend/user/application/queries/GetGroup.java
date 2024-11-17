package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.GroupModel;
import org.springframework.stereotype.Service;

@Service
public class GetGroup extends IQueryHandler<GetGroupQuery, GroupModel> {
}
