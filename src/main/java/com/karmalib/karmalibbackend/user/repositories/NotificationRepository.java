package com.karmalib.karmalibbackend.user.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository  extends JpaRepository<NotificationEntity, UUID> {
}
