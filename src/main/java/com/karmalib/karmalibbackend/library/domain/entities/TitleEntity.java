package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.library.domain.enums.ModerationStatus;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TranslationStatus;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ModerationStatus moderationStatus;
    @ElementCollection
    private List<String> alternateNames;

    @ManyToMany
    @JoinTable(
            name = "title_to_authors",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> translators;

    private LocalDateTime releaseDate;

    @ManyToMany
    @JoinTable(
            name = "title_to_genres",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genres;

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

    private PgRatings pgRating;
    @Builder.Default
    private boolean hentai = false;
    @Builder.Default
    private boolean ronabe = false;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<TitleTagEntity> tags;

    @OneToMany
    private List<RecommendationEntity> recommendations = new ArrayList<>();

    @OneToMany
    private List<CharacterEntity> characters = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "title_id")
    private List<ReviewEntity> reviews;
}

