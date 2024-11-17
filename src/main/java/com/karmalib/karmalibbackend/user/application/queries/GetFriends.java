package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.results.UserModel;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFriends implements IQueryHandler<GetFriendsQuery, List<UserModel>> {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> handle(final GetFriendsQuery query) {
        var users = userRepository.findFriendsByFilters(query.getUserId(), query.getName(), query.getOnline());
        List<UserModel> result = new ArrayList<>();

        for (var user : users ) {
            result.add(UserModel.fromEntity(user));
        }

        return result;
    }
}
