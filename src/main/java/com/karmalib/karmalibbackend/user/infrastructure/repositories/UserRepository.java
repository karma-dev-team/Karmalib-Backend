package com.karmalib.karmalibbackend.user.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    @Cacheable(value = "users", key = "#email")
    Optional<UserEntity> findByEmail(String email);
    @Cacheable(value = "users", key = "#username")
    Optional<UserEntity> findByUsername(String username);

    @Query("""
        SELECT f 
        FROM UserEntity u 
        JOIN u.friends f 
        WHERE u.id = :userId
          OR (:name IS NULL OR LOWER(f.username) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:online IS NULL OR f.isOnline = :online)
    """)
    List<UserEntity> findFriendsByFilters(
            @Param("userId") UUID userId,
            @Param("name") String name,
            @Param("online") Boolean online
    );
}
