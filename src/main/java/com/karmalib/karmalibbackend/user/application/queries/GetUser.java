package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.UserModel;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUser implements IQueryHandler<GetUserQuery, UserModel> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel handle(GetUserQuery query) {
        UserEntity user;

        if (query.getUserId() != null) {
            user = userRepository.findById(query.getUserId()).orElse(null);
        } else if (!query.getEmail().isEmpty()) {
            user = userRepository.findByEmail(query.getEmail()).orElse(null);
        } else {
            return null;
        }

        if (user == null) {
            return null;
        }
        return UserModel.fromEntity(user);
    }
}
