package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IPaginationService;
import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.common.domain.AccessDenied;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.application.queries.models.UserModel;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUsersList implements IQueryHandler<GetUsersListQuery, List<UserModel>> {
    @Autowired
    private AccessPolicy accessPolicy;


    @Autowired
    private UserRepository userRepository;

    public List<UserModel> handle(GetUsersListQuery query) throws AccessDenied {
        // TODO use query parameters
        if (!accessPolicy.isStaff()) {
            throw new AccessDenied("Need to be staff");
        }

        var users = userRepository.findAll();
        return users.stream().map(UserModel::fromEntity).collect(Collectors.toList());
    }
}
