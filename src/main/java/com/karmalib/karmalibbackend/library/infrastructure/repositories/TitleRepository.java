package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TranslationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, UUID> {
    @Query("SELECT t FROM TitleEntity t WHERE "
            + "(:keyword IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND "
            + "(:tags IS NULL OR EXISTS (SELECT 1 FROM t.tags tag WHERE tag.name IN :tags)) AND "
            + "(:excludeTags IS NULL OR NOT EXISTS (SELECT 1 FROM t.tags tag WHERE tag.name IN :excludeTags)) AND "
            + "(:genres IS NULL OR EXISTS (SELECT 1 FROM t.genres genre WHERE genre.name IN :genres)) AND "
            + "(:excludeGenres IS NULL OR NOT EXISTS (SELECT 1 FROM t.genres genre WHERE genre.name IN :excludeGenres)) AND "
            + "(:pgRatings IS NULL OR t.pgRating = :pgRatings) AND "
            + "(:titleStatus IS NULL OR t.status = :titleStatus) AND "
            + "(:translationStatus IS NULL OR t.translationStatus = :translationStatus) AND "
            + "(:startDate IS NULL OR t.createdAt >= :startDate) AND "
            + "(:endDate IS NULL OR t.createdAt <= :endDate) AND "
            + "(SIZE(t.reviews) BETWEEN :minReviews AND :maxReviews)"
    )
    List<TitleEntity> findByFilters(
            @Param("keyword") String keyword,
            @Param("tags") List<String> tags,
            @Param("excludeTags") List<String> excludeTags,
            @Param("genres") List<String> genres,
            @Param("excludeGenres") List<String> excludeGenres,
            @Param("pgRatings") PgRatings pgRatings,
            @Param("titleStatus") TitleStatus titleStatus,
            @Param("translationStatus") TranslationStatus translationStatus,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("minReviews") int minReviews,
            @Param("maxReviews") int maxReviews
    );
}
