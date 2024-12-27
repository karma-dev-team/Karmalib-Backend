package com.karmalib.karmalibbackend.forum.infrastructure.repositories;

import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    List<CommentEntity> findByAuthorId(UUID authorId);
}
