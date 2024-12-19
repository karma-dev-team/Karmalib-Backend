package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, UUID> {
    Optional<ChapterEntity> findByIndexAndTitleId(int index, UUID titleId);
}
