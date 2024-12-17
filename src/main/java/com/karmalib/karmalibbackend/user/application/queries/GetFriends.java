package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.UserModel;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFriends implements IQueryHandler<GetFriendsQuery, List<UserModel>> {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> handle(final GetFriendsQuery query) {
        var users = userRepository.findFriendsByFilters(query.getUserId(), query.getName(), query.getOnline());

        return users.stream().map(UserModel::fromEntity).toList();
    }
}
