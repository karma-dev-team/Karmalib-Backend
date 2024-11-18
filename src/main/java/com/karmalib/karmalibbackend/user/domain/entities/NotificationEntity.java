package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.user.domain.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "notification_id")
    private UserEntity recipient;

    private String content;
    private String title;
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleEntity titleEntity;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private CommentEntity comment;

    private boolean read;
}