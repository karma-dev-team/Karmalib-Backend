package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, UUID> {
    Optional<BookmarkEntity> findByUserIdAndTitleId(UUID userId, UUID titleId);
    List<BookmarkEntity> findAllByUserId(UUID userId);
    boolean existsByCategoryId(UUID categoryId);
}
