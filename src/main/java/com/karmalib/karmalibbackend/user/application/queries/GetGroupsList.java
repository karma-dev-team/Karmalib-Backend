package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.GroupModel;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetGroupsList  implements IQueryHandler<GetGroupsListQuery, List<GroupModel>> {
    @Autowired
    private GroupRepository groupRepository;

    public List<GroupModel> handle(GetGroupsListQuery query) {
        List<GroupEntity> groupEntities = groupRepository.findByUserIdAndBanned(query.getUserId(), query.isBanned());

        return groupEntities.stream()
                .map(GroupModel::fromEntity) // Преобразуем каждую сущность в модель
                .toList();
    }
}