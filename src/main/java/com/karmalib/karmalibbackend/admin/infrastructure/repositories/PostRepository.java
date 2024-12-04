package com.karmalib.karmalibbackend.admin.infrastructure.repositories;

import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
