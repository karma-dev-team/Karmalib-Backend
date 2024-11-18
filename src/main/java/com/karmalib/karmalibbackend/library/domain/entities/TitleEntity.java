package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "titles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleEntity extends BaseEntity {

    private String name;
    private String description;
    private String status;
    private String translationStatus;
    private String alternateNames;

    @ManyToOne
    @JoinColumn(name = "translator_id")
    private AuthorEntity translator;

    private LocalDateTime releaseDate;

    @ManyToMany
    @JoinTable(
            name = "title_to_categories",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> category;

    @OneToOne
    @JoinColumn(name = "cover_image_id")
    private FileEntity coverImage;

    @ManyToMany
    @JoinTable(
            name = "title_to_creator",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id")
    )
    private List<CreatorEntity> creators;

    private String pgRating;
    private boolean hentai = false;
    private boolean ronabe = false;
    private String moderationStatus;

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<TitleTagEntity> tags;

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<AuthorEntity> authors;
}

