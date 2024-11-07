package com.karmalib.karmalibbackend.user.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository  extends JpaRepository<NotificationEntity, UUID> {
}
