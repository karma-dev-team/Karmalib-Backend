package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {

    @ManyToOne
    public UserEntity user;

    @ManyToOne
    @JoinColumn(name = "title_id")
    public TitleEntity title;

    public int rating;
    public String content;
}