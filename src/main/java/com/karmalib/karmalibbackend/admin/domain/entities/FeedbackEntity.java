package com.karmalib.karmalibbackend.admin.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private String content;
    private String title;
    private String closeReaction;
}
