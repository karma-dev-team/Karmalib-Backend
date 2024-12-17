package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chapter_translations")
public class ChapterTranslationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}
