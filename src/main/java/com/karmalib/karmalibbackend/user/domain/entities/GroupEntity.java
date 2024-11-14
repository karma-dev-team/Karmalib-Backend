package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.application.exceptions.IncorrectInvitationException;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "groups")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity extends BaseEntity {
    private String name;
    private String description;
    private String shortDescription;
    @OneToMany
    private List<GroupInvitationEntity> invitations;

    @OneToMany
    private List<GroupContactEntity> contacts;

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
}
