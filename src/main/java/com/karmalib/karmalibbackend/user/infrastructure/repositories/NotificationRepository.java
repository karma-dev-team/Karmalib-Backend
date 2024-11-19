package com.karmalib.karmalibbackend.user.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.domain.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository  extends JpaRepository<NotificationEntity, UUID> {
    List<NotificationEntity> findAllByRecipientId(UUID recipientId);

    // Найти уведомления для пользователя определённого типа
    List<NotificationEntity> findAllByRecipientIdAndType(UUID userId, NotificationType type);
}
