package com.karmalib.karmalibbackend.user.infrastructure.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

}
