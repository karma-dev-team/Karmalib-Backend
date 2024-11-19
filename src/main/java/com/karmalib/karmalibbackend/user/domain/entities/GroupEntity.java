package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.application.exceptions.IncorrectInvitationException;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import com.karmalib.karmalibbackend.user.domain.events.GroupIntegrationAdded;
import com.karmalib.karmalibbackend.user.domain.events.GroupOwnershipHandOver;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "groups")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity extends BaseEntity {
    private String name;
    private String description;
    private String shortDescription;
    @OneToMany
    private List<GroupInvitationEntity> invitations;

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<GroupIntegrationEntity> integrations;

    private boolean isBanned = false;
    private boolean isPendingDeletion = false;
    private boolean isDeletionRequestedByAdmin = false;

    @ManyToMany
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;

    @ManyToOne
    @JoinTable(
            name= "owner_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserEntity owner;

    public void addUser(GroupInvitationEntity invitation, UserEntity user) throws IncorrectInvitationException {
        if (!invitations.contains(invitation)) {
            throw new IncorrectInvitationException(invitation.id);
        }
        if (invitation.getStatus() != InvitationStatus.Accepted) {
            invitation.accept();
        }
        users.add(user);
    }

    public void addIntegration(GroupIntegrationEntity integration) {
        this.integrations.add(integration);

        // Генерируем событие
        GroupIntegrationAdded event = new GroupIntegrationAdded(this.id, integration.getType(), integration.getLink());
        this.addDomainEvent(event);
    }

    public void changeOwnership(UserEntity newOwner) {
        this.owner = newOwner;

        // Создаем событие о передаче владения и добавляем его к доменным событиям
        GroupOwnershipHandOver event = new GroupOwnershipHandOver(this.id, newOwner.id);
        this.addDomainEvent(event);
    }
}
