package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.forum.domain.events.CommentLikeAdded;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

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
    @Builder.Default
    private Boolean isDeleted = false;
    @Builder.Default
    private Boolean isSpoiler = false;

    private int likes;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private CommentEntity parentComment; // Родительский комментарий

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<CommentEntity> replies = new ArrayList<>(); // Список ответов


    @Builder.Default
    private boolean pinned = false;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment", orphanRemoval = true)
    private List<ReactionEntity> reactions = new ArrayList<>(); // Список реакций

    public boolean hasReaction(UserEntity user, ReactionType type) {
        return reactions.stream()
                .anyMatch(reaction -> reaction.getUser().id.equals(user.id) && reaction.getType() == type);
    }

    // Добавление реакции
    public void addReaction(UserEntity user, ReactionType type) {
        if (hasReaction(user, type)) {
            throw new IllegalStateException("User has already added this reaction to the comment");
        }

        // Добавляем новую реакцию
        ReactionEntity newReaction = ReactionEntity.builder()
                .user(user)
                .comment(this)
                .type(type)
                .build();

        reactions.add(newReaction);
    }

    // Удаление реакции
    public void removeReaction(UserEntity user, ReactionType type) {
        // Ищем реакцию для удаления
        ReactionEntity reactionToRemove = reactions.stream()
                .filter(reaction -> reaction.getUser().id.equals(user.id) && reaction.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User hasn't added this reaction to the comment"));

        // Удаляем реакцию
        reactions.remove(reactionToRemove);
    }

    // Подсчет лайков
    public long getLikesCount() {
        return reactions.stream()
                .filter(reaction -> reaction.getType() == ReactionType.LIKE)
                .count();
    }

    // Подсчет дизлайков
    public long getDislikesCount() {
        return reactions.stream()
                .filter(reaction -> reaction.getType() == ReactionType.DISLIKE)
                .count();
    }
}
