package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
    @OneToMany
    @JoinColumn
    private List<CommentEntity> comments;
}
