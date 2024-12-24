package com.karmalib.karmalibbackend.user.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.CustomResponseEntity;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.user.application.commands.*;
import com.karmalib.karmalibbackend.user.application.queries.*;
import com.karmalib.karmalibbackend.user.application.queries.models.*;
import com.karmalib.karmalibbackend.user.application.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    // -------- Командные методы --------


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        DeleteAccountCommand command = DeleteAccountCommand.builder().userId(userId).build();
        CommandResult result = userCommandService.delete(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/{userId}/suspend")
    public ResponseEntity<?> suspendUser(@PathVariable UUID userId) {
        SuspendUserCommand command = SuspendUserCommand.builder().userId(userId).build();
        CommandResult result = userCommandService.suspend(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(@RequestBody AddFriendCommand command) {
        CommandResult result = userCommandService.addFriend(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<?> deleteFriend(@PathVariable UUID friendId) {
        DeleteFriendCommand command = DeleteFriendCommand.builder().friendId(friendId).build();
        CommandResult result = userCommandService.deleteFriend(command);
        return RestService.buildResponse(result);
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<?> updateEmail(@PathVariable UUID userId, @RequestBody UpdateEmailCommand command) {
        command.setUserId(userId);
        CommandResult result = userCommandService.updateEmail(command);
        return RestService.buildResponse(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody UpdateUserCommand command) {
        command.setUserId(userId);
        CommandResult result = userCommandService.updateUser(command);
        return RestService.buildResponse(result);
    }

    // -------- Методы запросов --------

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?> getUserFriends(@PathVariable UUID userId) {
        GetFriendsQuery query = GetFriendsQuery.builder().userId(userId).build();
        List<UserModel> friends = userQueryService.getUserFriends(query);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable UUID userId) {
        GetUserQuery query = GetUserQuery.builder().userId(userId).build();
        return ResponseEntity.ok(userQueryService.getUser(query));
    }

    @GetMapping("/{userId}/confidential-info")
    public ResponseEntity<?> getConfidentialInfo(@PathVariable UUID userId) {
        GetConfidentialInfoQuery query = GetConfidentialInfoQuery.builder().userId(userId).build();
        ConfidentialInfoModel info = userQueryService.getConfidentialInfo(query);
        return ResponseEntity.ok(info);
    }

    @GetMapping("/{userId}/notifications/settings")
    public ResponseEntity<?> getNotificationSettings(@PathVariable UUID userId) {
        GetNotificationSettingsQuery query = GetNotificationSettingsQuery.builder().userId(userId).build();
        NotificationSettingsModel settings = userQueryService.getNotificationSettings(query);
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<?> getUserNotifications(@PathVariable UUID userId) {
        GetUserNotificationsQuery query = GetUserNotificationsQuery.builder().userId(userId).build();
        List<NotificationModel> notifications = userQueryService.getUserNotifications(query);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/notifications/read")
    public ResponseEntity<?> readUserNotifications(@RequestBody ReadNotificationsCommand command)  {
        CommandResult result = userCommandService.readNotifications(command);
        return RestService.buildResponse(result);
    }
}
