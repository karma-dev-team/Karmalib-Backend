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
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmailOrUsername(String email, String username);
    Optional<UserEntity> findByEmail(String email);
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
