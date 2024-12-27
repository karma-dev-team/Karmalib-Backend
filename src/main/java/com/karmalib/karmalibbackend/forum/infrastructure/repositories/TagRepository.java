package com.karmalib.karmalibbackend.forum.infrastructure.repositories;

import com.karmalib.karmalibbackend.forum.domain.entities.PostTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<PostTagEntity, UUID> {
}
