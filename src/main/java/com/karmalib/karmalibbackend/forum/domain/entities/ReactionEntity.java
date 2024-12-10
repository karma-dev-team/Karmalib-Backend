package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactionEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // Пользователь, оставивший реакцию

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private CommentEntity comment; // Комментарий, к которому относится реакция (если есть)

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private PostEntity post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type; // Тип реакции (LIKE, DISLIKE и т.д.)
}
