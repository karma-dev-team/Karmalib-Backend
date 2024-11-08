package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "posts")
public class PostEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
}
