package com.karmalib.karmalibbackend.user.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.user.application.commands.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GroupCommandService {

    private final CreateGroup createGroupHandler;
    private final UpdateGroup updateGroupHandler;
    private final DeleteGroup deleteGroupHandler;
    private final CancelGroupDeletion cancelGroupDeletionHandler;
    private final GiveOwnershipOfGroup giveOwnershipOfGroupHandler;
    private final InviteUserToGroup inviteUserToGroupHandler;
    private final KickUserFromGroup kickUserFromGroupHandler;
    private final RespondToInvitation respondToInvitationHandler;
    private final AddIntegration addIntegrationHandler;

    public GroupCommandService(
            CreateGroup createGroupHandler,
            DeleteGroup deleteGroupHandler,
            CancelGroupDeletion cancelGroupDeletionHandler,
            GiveOwnershipOfGroup giveOwnershipOfGroupHandler,
            InviteUserToGroup inviteUserToGroupHandler,
            KickUserFromGroup kickUserFromGroupHandler,
            RespondToInvitation respondToInvitationHandler,
            UpdateGroup updateGroupHandler,
            AddIntegration addIntegrationHandler
    ) {
        this.addIntegrationHandler = addIntegrationHandler;
        this.updateGroupHandler = updateGroupHandler;
        this.respondToInvitationHandler = respondToInvitationHandler;
        this.createGroupHandler = createGroupHandler;
        this.deleteGroupHandler = deleteGroupHandler;
        this.cancelGroupDeletionHandler = cancelGroupDeletionHandler;
        this.giveOwnershipOfGroupHandler = giveOwnershipOfGroupHandler;
        this.inviteUserToGroupHandler = inviteUserToGroupHandler;
        this.kickUserFromGroupHandler = kickUserFromGroupHandler;
    }

    public CommandResult updateGroup(UpdateGroupCommand command) {
        return updateGroupHandler.handle(command);
    }

    public CommandResult respondToInvitation(RespondToInvitationCommand command) {
        return respondToInvitationHandler.handle(command);
    }

    public CommandResult addIntegration(AddIntegrationCommand command) {
        return addIntegrationHandler.handle(command);
    }

    // Создание группы
    public CommandResult createGroup(CreateGroupCommand command) {
        return createGroupHandler.handle(command);
    }

    // Удаление группы
    public CommandResult deleteGroup(DeleteGroupCommand command) {
        return deleteGroupHandler.handle(command);
    }

    // Отмена удаления группы
    public CommandResult cancelGroupDeletion(CancelGroupDeletionCommand command) {
        return cancelGroupDeletionHandler.handle(command);
    }

    // Передача прав владельца группы
    public CommandResult giveOwnershipOfGroup(GiveOwnershipOfGroupCommand command) {
        return giveOwnershipOfGroupHandler.handle(command);
    }

    // Приглашение пользователя в группу
    public CommandResult inviteUserToGroup(InviteUserToGroupCommand command) {
        return inviteUserToGroupHandler.handle(command);
    }

    // Удаление пользователя из группы
    public CommandResult kickUserFromGroup(KickUserFromGroupCommand command) {
        return kickUserFromGroupHandler.handle(command);
    }
}
