package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.user.application.queries.GetGroup;
import com.karmalib.karmalibbackend.user.application.queries.GetGroupQuery;
import com.karmalib.karmalibbackend.user.application.queries.GetGroupsList;
import com.karmalib.karmalibbackend.user.application.queries.GetGroupsListQuery;
import com.karmalib.karmalibbackend.user.application.queries.results.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupQueryService {

    private final GetGroup getGroupHandler;
    private final GetGroupsList getGroupsListHandler;

    @Autowired
    public GroupQueryService(GetGroup getGroupHandler, GetGroupsList getGroupsListHandler) {
        this.getGroupHandler = getGroupHandler;
        this.getGroupsListHandler = getGroupsListHandler;
    }

    // Получить информацию о конкретной группе
    public GroupModel getGroup(GetGroupQuery query) {
        return getGroupHandler.handle(query);
    }

    // Получить список всех групп
    public List<GroupModel> getGroupsList(GetGroupsListQuery query) {
        return getGroupsListHandler.handle(query);
    }
}