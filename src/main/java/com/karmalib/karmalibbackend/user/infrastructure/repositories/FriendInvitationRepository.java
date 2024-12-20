package com.karmalib.karmalibbackend.user.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.FriendInvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FriendInvitationRepository extends CrudRepository<FriendInvitationEntity, UUID> {
}
