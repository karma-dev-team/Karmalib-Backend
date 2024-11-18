package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.user.application.queries.*;
import com.karmalib.karmalibbackend.user.application.queries.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    public UserQueryService(
        GetFriends getFriends,
        GetConfidentialInfo getConfidentialInfo,
        GetGroup getGroup,
        GetUser getUser,
        GetGroupsList getGroupsList,
        GetHistoryOfProfile getHistoryOfProfile,
        GetNotificationSettings getNotificationSettings,
        GetUserNotifications getUserNotifications
    ) {
        this.getFriends = getFriends;
        this.getConfidentialInfo = getConfidentialInfo;
        this.getGroup = getGroup;
        this.getUser = getUser;
        this.getGroupsList = getGroupsList;
        this.getHistoryOfProfile = getHistoryOfProfile;
        this.getNotificationSettings = getNotificationSettings;
        this.getUserNotifications = getUserNotifications;
    }

    // Получить друзей пользователя
    public List<UserModel> getUserFriends(GetFriendsQuery query) {
        return this.getFriends.handle(query);
    }

    // Получить конфиденциальную информацию
    public ConfidentialInfoModel getConfidentialInfo(GetConfidentialInfoQuery query) {
        return this.getConfidentialInfo.handle(query);
    }

    // Получить информацию о группе
    public GroupModel getGroup(GetGroupQuery query) {
        return this.getGroup.handle(query);
    }

    // Получить информацию о пользователе
    public UserModel getUser(GetUserQuery query) {
        return this.getUser.handle(query);
    }

    // Получить список групп
    public List<GroupModel> getGroupsList(GetGroupsListQuery query) {
        return this.getGroupsList.handle(query);
    }

    // Получить историю профиля
//    public List<HistoryModel> getHistoryOfProfile(GetHistoryOfProfileQuery query) {
//        return this.getHistoryOfProfile.handle(query);
//    }

    // Получить настройки уведомлений
    public NotificationSettingsModel getNotificationSettings(GetNotificationSettingsQuery query) {
        return this.getNotificationSettings.handle(query);
    }

    // Получить уведомления пользователя
    public List<NotificationModel> getUserNotifications(GetUserNotificationsQuery query) {
        return this.getUserNotifications.handle(query);
    }
}
