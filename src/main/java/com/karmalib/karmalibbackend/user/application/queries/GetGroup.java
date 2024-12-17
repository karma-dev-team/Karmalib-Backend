package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.GroupModel;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetGroup implements IQueryHandler<GetGroupQuery, GroupModel> {
    @Autowired
    private GroupRepository groupRepository;

    public GroupModel handle( GetGroupQuery query) {
        GroupEntity group = groupRepository.findById(query.getGroupId()).orElse(null);
        if (group == null) {
            return null;
        }

        return GroupModel.fromEntity(group);
    }
}
