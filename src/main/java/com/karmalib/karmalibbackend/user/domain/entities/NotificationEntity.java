package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String message;
    private String category;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleEntity title;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private FileEntity image;

    private boolean read;
}