package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "titles")
public class TitleEntity extends BaseEntity {

    public String name;
    public String description;
    public String status;
    public String translationStatus;
    public String alternateNames;

    @ManyToOne
    @JoinColumn(name = "translator_id")
    public UserEntity translator;

    public LocalDateTime releaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public CategoryEntity category;

    @OneToOne
    @JoinColumn(name = "cover_image_id")
    public FileEntity coverImage;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    public AuthorEntity originalAuthor;

    public String pgRating;
    public boolean hentai = false;
    public boolean ronabe = false;
    public String moderationStatus;

    @OneToMany(mappedBy = "title")
    public List<TitleTagEntity> tags;
}

