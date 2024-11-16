package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.user.application.queries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final GetFriends getFriends;
    private final GetConfidentialInfo getConfidentialInfo;
    private final GetGroup getGroup;
    private final GetUser getUser;
    private final GetGroupsList getGroupsList;
    private final GetHistoryOfProfile getHistoryOfProfile;
    private final GetNotificationSettings getNotificationSettings;
    private final GetUserNotifications getUserNotifications;

    public UserQueryService(GetFriends getFriends, GetConfidentialInfo getConfidentialInfo, GetGroup getGroup, GetUser getUser, GetGroupsList getGroupsList, GetHistoryOfProfile getHistoryOfProfile, GetNotificationSettings getNotificationSettings, GetUserNotifications getUserNotifications) {
        this.getFriends = getFriends;
        this.getConfidentialInfo = getConfidentialInfo;
        this.getGroup = getGroup;
        this.getUser = getUser;
        this.getGroupsList = getGroupsList;
        this.getHistoryOfProfile = getHistoryOfProfile;
        this.getNotificationSettings = getNotificationSettings;
        this.getUserNotifications = getUserNotifications;
    }
}
