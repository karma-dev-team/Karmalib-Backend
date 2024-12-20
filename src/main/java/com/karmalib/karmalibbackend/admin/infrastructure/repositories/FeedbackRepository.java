package com.karmalib.karmalibbackend.admin.infrastructure.repositories;

import com.karmalib.karmalibbackend.admin.domain.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, UUID> {
}
