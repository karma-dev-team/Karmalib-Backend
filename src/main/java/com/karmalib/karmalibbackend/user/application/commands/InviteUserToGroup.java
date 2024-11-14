package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.domain.entities.GroupInvitationEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupInvitationRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InviteUserToGroup implements ICommandHandler<InviteUserToGroupCommand> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupInvitationRepository invitationRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(InviteUserToGroupCommand command) {
        UUID currentUserId = accessPolicy.getCurrentUser().id;
        GroupEntity group = groupRepository.findById(command.getGroupId()).orElse(null);
        if (group == null) {
            return CommandResult.failure("Группа не найдена");
        }

        // Проверка, является ли текущий пользователь владельцем группы
        if (!(group.getOwner().id == currentUserId)) {
            return CommandResult.failure("Только создатель группы может отправлять приглашения");
        }

        // Поиск пользователя и группы
        UserEntity invitedUser = userRepository.findById(command.getInvitedUserId()).orElse(null);
        if (invitedUser == null) {
            return CommandResult.failure("Invited user not found");
        }

        // Создание приглашения
        GroupInvitationEntity invitation = GroupInvitationEntity.builder()
                .group(group)
                .user(invitedUser)
                .build();
        invitationRepository.save(invitation);

        return CommandResult.success(invitation.id);
    }
}
