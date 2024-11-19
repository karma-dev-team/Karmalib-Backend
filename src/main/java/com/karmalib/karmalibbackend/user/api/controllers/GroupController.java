package com.karmalib.karmalibbackend.user.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.*;
import com.karmalib.karmalibbackend.user.application.queries.GetGroupQuery;
import com.karmalib.karmalibbackend.user.application.queries.GetGroupsListQuery;
import com.karmalib.karmalibbackend.user.application.queries.results.GroupModel;
import com.karmalib.karmalibbackend.user.application.services.GroupCommandService;
import com.karmalib.karmalibbackend.user.application.services.GroupQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupCommandService groupCommandService;
    private final GroupQueryService groupQueryService;

    public GroupController(GroupCommandService groupCommandService, GroupQueryService groupQueryService) {
        this.groupCommandService = groupCommandService;
        this.groupQueryService = groupQueryService;
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

    // ------- Запросы --------
    @GetMapping("/{userId}/groups/{groupId}")
    public ResponseEntity<?> getGroup(@PathVariable UUID userId, @PathVariable UUID groupId) {
        GetGroupQuery query = GetGroupQuery.builder().groupId(groupId).build();
        GroupModel group = groupQueryService.getGroup(query);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{userId}/groups")
    public ResponseEntity<?> getGroupsList(@PathVariable UUID userId) {
        GetGroupsListQuery query = GetGroupsListQuery.builder().userId(userId).build();
        List<GroupModel> groups = groupQueryService.getGroupsList(query);
        return ResponseEntity.ok(groups);
    }
}

