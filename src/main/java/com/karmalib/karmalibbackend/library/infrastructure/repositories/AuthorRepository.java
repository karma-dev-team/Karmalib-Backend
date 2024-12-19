package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    List<AuthorEntity> findAllByIsAllowedToPost(Boolean isAllowedToPost);
    Optional<AuthorEntity> findByUserId(UUID userId);
    Optional<AuthorEntity> findByGroupId(UUID groupId);
}
