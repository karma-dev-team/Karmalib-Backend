package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chapter_translations")
public class ChapterTranslationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    public ChapterEntity chapter;

    public LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    public UserEntity author;
}
