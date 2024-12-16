package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.library.domain.enums.TitleModerationStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TranslationStatus;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "titles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleEntity extends BaseEntity {

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TitleStatus status;
    @Enumerated(EnumType.STRING)
    private TranslationStatus translationStatus;
    private TitleModerationStatus moderationStatus;
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

    @ManyToMany
    @JoinTable(
            name = "title_to_genres",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genre;

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

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<CommentEntity> comments;

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<TitleTagEntity> tags;

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<AuthorEntity> authors;

    @OneToMany
    private Set<RecommendationEntity> recommendations;

    @OneToMany
    private Set<CharacterEntity> characters;

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<ReviewEntity> reviews;
}

