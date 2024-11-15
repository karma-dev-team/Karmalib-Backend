package com.karmalib.karmalibbackend.forum.infrastructure.repositories;

import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
