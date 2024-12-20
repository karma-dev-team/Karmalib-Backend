package com.karmalib.karmalibbackend.user.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, UUID> {
    boolean existsByName(String name);
    List<GroupEntity> findByIsPendingDeletionTrue();
    @Query("SELECT g FROM GroupEntity g " +
            "WHERE (g.owner.id = :userId) " +
            "AND g.isBanned = :banned")
    List<GroupEntity> findByUserIdAndBanned(@Param("userId") UUID userId, @Param("banned") boolean banned);
}
