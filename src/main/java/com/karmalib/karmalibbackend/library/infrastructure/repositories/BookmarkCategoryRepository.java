package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.BookmarkCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookmarkCategoryRepository extends JpaRepository<BookmarkCategoryEntity, UUID> {
    List<BookmarkCategoryEntity> findAllByUserId(UUID userId);
}
