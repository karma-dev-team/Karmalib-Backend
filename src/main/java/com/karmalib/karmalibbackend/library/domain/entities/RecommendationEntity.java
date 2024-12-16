package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.ReactionEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "title_recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private TitleEntity title;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserEntity creator;

    @OneToMany
    @JoinColumn(name = "recommendation_id")
    private Set<ReactionEntity> reactions = new HashSet<>();
    private int rating;
    @Builder.Default
    private Boolean active = false;

    public boolean hasReaction(UserEntity user, ReactionType type) {
        return reactions.stream()
                .anyMatch(reaction -> reaction.getUser().id.equals(user.id) && reaction.getType() == type);
    }

    // Добавление реакции
    public boolean addReaction(UserEntity user, ReactionType type) {
        if (hasReaction(user, type)) {
            return false;
        }

        // Добавляем новую реакцию
        ReactionEntity newReaction = ReactionEntity.builder()
                .user(user)
                .type(type)
                .build();

        if (ReactionType.LIKE == type) {
            rating++;
        } else {
            rating--;
        }

        reactions.add(newReaction);

        return true; 
    }
}
