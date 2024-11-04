package com.karmalib.karmalibbackend.admin.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Getter
@Setter
public class NewsEntity extends BaseEntity {
    private String headline;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private FileEntity image;
}