package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import com.karmalib.karmalibbackend.user.domain.events.GroupInvitationResponded;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_invitations")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private InvitationStatus status = InvitationStatus.Pending;

    public void accept() {
        respond(InvitationStatus.Accepted);
    }

    public void respond(InvitationStatus status) {
        this.status = status;
        addDomainEvent(new GroupInvitationResponded(status, this.id, this.group.id, this.user.id));
    }

    public void decline() {
        respond(InvitationStatus.Declined);
    }
}
