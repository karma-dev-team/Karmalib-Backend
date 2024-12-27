package com.karmalib.karmalibbackend.forum.infrastructure.repositories;

import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    // Фильтрация по тегам с сортировкой по количеству лайков
    @Query("SELECT p FROM PostEntity p JOIN p.tags t WHERE t.name IN :tags GROUP BY p.id ORDER BY p.likesCount DESC")
    List<PostEntity> findByTagsOrderByLikesCountDesc(@Param("tags") List<String> tags);

    // Фильтрация по тегам с сортировкой по дате создания
    @Query("SELECT p FROM PostEntity p JOIN p.tags t WHERE t.name IN :tags GROUP BY p.id ORDER BY p.createdAt DESC")
    List<PostEntity> findByTagsOrderByCreatedAtDesc(@Param("tags") List<String> tags);

    // Все посты, сортировка по количеству лайков
    List<PostEntity> findAllByOrderByLikesCountDesc();

    // Все посты, сортировка по дате создания
    List<PostEntity> findAllByOrderByCreatedAtDesc();
}
