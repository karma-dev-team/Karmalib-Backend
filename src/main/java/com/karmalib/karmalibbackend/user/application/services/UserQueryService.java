package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.user.application.queries.GetFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    @Autowired
    private GetFriends getFriends;

}
