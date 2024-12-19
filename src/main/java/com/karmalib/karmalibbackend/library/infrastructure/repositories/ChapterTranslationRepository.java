package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.ChapterTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChapterTranslationRepository extends JpaRepository<ChapterTranslationEntity, UUID> {
}
