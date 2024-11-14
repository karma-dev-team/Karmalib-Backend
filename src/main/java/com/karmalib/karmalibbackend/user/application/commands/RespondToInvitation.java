package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.application.exceptions.IncorrectInvitationException;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.domain.entities.GroupInvitationEntity;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupInvitationRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RespondToInvitation implements ICommandHandler<RespondToInvitationCommand> {

    @Autowired
    private GroupInvitationRepository invitationRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(RespondToInvitationCommand command) {
        UUID currentUserId = accessPolicy.getCurrentUser().id;

        // Поиск приглашения
        GroupInvitationEntity invitation = invitationRepository.findById(command.getInvitationId()).orElse(null);
        if (invitation == null) {
            return CommandResult.failure("Invitation not found");
        }

        // Проверка, что текущий пользователь — тот, кто был приглашен
        if (!invitation.getUser().id.equals(currentUserId)) {
            return CommandResult.failure("Access denied");
        }

        // Обновление статуса приглашения
        if (command.isAccepted()) {
            invitation.respond(InvitationStatus.Accepted);

            // Добавление пользователя в группу
            GroupEntity group = invitation.getGroup();
            try {
                group.addUser(invitation, invitation.getUser());
            } catch (IncorrectInvitationException exc) {
                return CommandResult.failure("Incorrect invitation");
            }
            groupRepository.save(group);
        } else {
            invitation.respond(InvitationStatus.Declined);
        }

        invitationRepository.save(invitation);
        return CommandResult.success(invitation.id);
    }
}
