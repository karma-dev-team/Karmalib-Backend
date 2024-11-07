package com.karmalib.karmalibbackend.user.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

}
