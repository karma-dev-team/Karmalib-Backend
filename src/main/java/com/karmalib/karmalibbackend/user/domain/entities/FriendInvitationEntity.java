package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.enums.InvitationStatus;
import com.karmalib.karmalibbackend.user.domain.events.FriendInvitationAccepted;
import com.karmalib.karmalibbackend.user.domain.events.FriendInvitationDeclined;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "friend_invitations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendInvitationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private InvitationStatus status = InvitationStatus.PENDING;

    @Column(name = "sent_at", nullable = false)
    @Builder.Default
    private LocalDateTime sentAt = LocalDateTime.now();

    public FriendInvitationEntity(UserEntity sender, UserEntity receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = InvitationStatus.PENDING;
        this.sentAt = LocalDateTime.now();
    }

    // Метод для принятия приглашения
    public void accept() {
        this.status = InvitationStatus.ACCEPTED;
        sender.addFriend(receiver);
        addDomainEvent(new FriendInvitationAccepted(sender.id, receiver.id));
    }

    // Метод для отклонения приглашения
    public void decline() {
        this.status = InvitationStatus.DECLINED;
        addDomainEvent(new FriendInvitationDeclined(sender.id, receiver.id));
    }
}