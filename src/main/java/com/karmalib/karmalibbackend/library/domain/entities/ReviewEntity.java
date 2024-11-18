package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleEntity title;

    private int rating;
    private String content;
}