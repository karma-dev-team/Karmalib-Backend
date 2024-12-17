package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity extends BaseEntity {

    // Автор это переводчик
    @ManyToOne
    private GroupEntity group;

    @ManyToOne
    private UserEntity user;

    @Builder.Default
    private Boolean isAllowedToPost = true;
}