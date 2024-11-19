package com.karmalib.karmalibbackend.user.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.*;
import com.karmalib.karmalibbackend.user.application.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService userCommandService;

    public UserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    private ResponseEntity<?> buildResponse(CommandResult result) {
        return ResponseEntity.status(result.getIsSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "success", result.getIsSuccess(),
                        "id", result.getId(),
                        "description", result.getMessage()
                ));
    }

    // Регистрация пользователя
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CreateUserCommand command) {
        CommandResult result = userCommandService.register(command);
        return buildResponse(result);
    }

    // Удаление аккаунта
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        DeleteAccountCommand command = DeleteAccountCommand.builder().userId(userId).build();
        CommandResult result = userCommandService.delete(command);
        return buildResponse(result);
    }

    // Приостановка пользователя
    @PostMapping("/{userId}/suspend")
    public ResponseEntity<?> suspendUser(@PathVariable UUID userId) {
        SuspendUserCommand command = SuspendUserCommand.builder().userId(userId).build();
        CommandResult result = userCommandService.suspend(command);
        return buildResponse(result);
    }

    // Добавление друга
    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(
            @RequestBody AddFriendCommand command) {
        CommandResult result = userCommandService.addFriend(command);
        return buildResponse(result);
    }

    // Удаление друга
    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<?> deleteFriend(@PathVariable UUID friendId) {
        DeleteFriendCommand command = DeleteFriendCommand.builder()
                .friendId(friendId)
                .build();
        CommandResult result = userCommandService.deleteFriend(command);
        return buildResponse(result);
    }

    // Обновление email пользователя
    @PutMapping("/{userId}/email")
    public ResponseEntity<?> updateEmail(
            @PathVariable UUID userId,
            @RequestBody UpdateEmailCommand command) {
        command.setUserId(userId); // Установка ID пользователя
        CommandResult result = userCommandService.updateEmail(command);
        return buildResponse(result);
    }

    // Обновление информации о пользователе
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID userId,
            @RequestBody UpdateUserCommand command) {
        command.setUserId(userId); // Установка ID пользователя
        CommandResult result = userCommandService.updateUser(command);
        return buildResponse(result);
    }
}