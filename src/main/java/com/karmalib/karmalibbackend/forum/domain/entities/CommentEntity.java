package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

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
}
