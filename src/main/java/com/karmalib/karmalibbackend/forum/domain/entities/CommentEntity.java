package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.events.CommentLikeAdded;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "comments")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseEntity {
    @ManyToOne
    private UserEntity author;
    private String text;
    private Boolean isDeleted = false;
    private Boolean isSpoiler = false;

    private int likes;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private CommentEntity parentComment; // Родительский комментарий

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<CommentEntity> replies = new ArrayList<>(); // Список ответов

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment", orphanRemoval = true)
    private List<CommentLikeEntity> likesList = new ArrayList<>(); // Список лайков


    // Добавление лайка
    public void addLike(UserEntity user) {
        // Проверяем, лайкнул ли уже пользователь
        boolean alreadyLiked = likesList.stream().anyMatch(like -> like.getUser().id.equals(user.id));
        if (alreadyLiked) {
            throw new IllegalStateException("User has already liked this comment");
        }

        // Добавляем лайк
        CommentLikeEntity newLike = CommentLikeEntity.builder()
                .user(user)
                .comment(this)
                .likedAt(LocalDateTime.now())
                .build();
        likesList.add(newLike);

        // Увеличиваем счётчик лайков
        this.likes++;
    }

    // Удаление лайка
    public void removeLike(UserEntity user) {
        // Проверяем, существует ли лайк от пользователя
        CommentLikeEntity likeToRemove = likesList.stream()
                .filter(like -> like.getUser().id.equals(user.id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User hasn't liked this comment"));

        // Удаляем лайк
        likesList.remove(likeToRemove);

        // Уменьшаем счётчик лайков
        this.likes--;
    }
}
