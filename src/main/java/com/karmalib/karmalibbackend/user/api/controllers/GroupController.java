package com.karmalib.karmalibbackend.user.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.*;
import com.karmalib.karmalibbackend.user.application.services.GroupCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupCommandService groupCommandService;

    public GroupController(GroupCommandService groupCommandService) {
        this.groupCommandService = groupCommandService;
    }

    private ResponseEntity<?> buildResponse(CommandResult result) {
        return ResponseEntity.status(result.getIsSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "success", result.getIsSuccess(),
                        "id", result.getId(),
                        "description", result.getMessage()
                ));
    }

    // Создание группы
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupCommand command) {
        CommandResult result = groupCommandService.createGroup(command);
        return buildResponse(result);
    }

    // Обновление группы
    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(
            @PathVariable UUID groupId,
            @RequestBody UpdateGroupCommand command) {
        command.setGroupId(groupId); // Устанавливаем ID группы
        CommandResult result = groupCommandService.updateGroup(command);
        return buildResponse(result);
    }

    // Удаление группы
    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable UUID groupId) {
        DeleteGroupCommand command = DeleteGroupCommand.builder().groupId(groupId).build();
        CommandResult result = groupCommandService.deleteGroup(command);
        return buildResponse(result);
    }

    // Отмена удаления группы
    @PostMapping("/{groupId}/cancel-deletion")
    public ResponseEntity<?> cancelGroupDeletion(@PathVariable UUID groupId) {
        CancelGroupDeletionCommand command = CancelGroupDeletionCommand.builder().groupId(groupId).build();
        CommandResult result = groupCommandService.cancelGroupDeletion(command);
        return buildResponse(result);
    }

    // Передача прав владельца группы
    @PostMapping("/{groupId}/transfer-ownership")
    public ResponseEntity<?> giveOwnershipOfGroup(
            @PathVariable UUID groupId,
            @RequestBody GiveOwnershipOfGroupCommand command) {
        command.setGroupId(groupId);
        CommandResult result = groupCommandService.giveOwnershipOfGroup(command);
        return buildResponse(result);
    }

    // Приглашение пользователя в группу
    @PostMapping("/{groupId}/invite")
    public ResponseEntity<?> inviteUserToGroup(
            @PathVariable UUID groupId,
            @RequestBody InviteUserToGroupCommand command) {
        command.setGroupId(groupId);
        CommandResult result = groupCommandService.inviteUserToGroup(command);
        return buildResponse(result);
    }

    // Удаление пользователя из группы
    @PostMapping("/{groupId}/kick")
    public ResponseEntity<?> kickUserFromGroup(
            @PathVariable UUID groupId,
            @RequestBody KickUserFromGroupCommand command) {
        command.setGroupId(groupId);
        CommandResult result = groupCommandService.kickUserFromGroup(command);
        return buildResponse(result);
    }

    // Ответ на приглашение
    @PostMapping("/{groupId}/respond-invitation")
    public ResponseEntity<?> respondToInvitation(
            @PathVariable UUID groupId,
            @RequestBody RespondToInvitationCommand command) {
        CommandResult result = groupCommandService.respondToInvitation(command);
        return buildResponse(result);
    }

    // Добавление интеграции
    @PostMapping("/{groupId}/integrations")
    public ResponseEntity<?> addIntegration(
            @PathVariable UUID groupId,
            @RequestBody AddIntegrationCommand command) {
        command.setGroupId(groupId);
        CommandResult result = groupCommandService.addIntegration(command);
        return buildResponse(result);
    }
}

