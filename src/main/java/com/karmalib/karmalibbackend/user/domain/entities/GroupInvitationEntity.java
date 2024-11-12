package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_invitations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupInvitationEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "invitation_id")
    private UserEntity user;

    @Column(nullable = false)
    @Builder.Default
    private boolean accepted = false;

    public void accept() { } // TODO
    public void reject() { } // TODO
}
