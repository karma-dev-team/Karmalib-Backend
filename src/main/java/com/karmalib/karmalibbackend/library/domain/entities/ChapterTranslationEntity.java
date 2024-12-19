package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chapter_translations")
public class ChapterTranslationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    private LocalDateTime delayedPublicationDate;
    // Custom parsing for models
    @ManyToMany
    @JoinTable(
        name = "chapter_to_segments",
        inverseJoinColumns = @JoinColumn(name = "segment_id"),
        joinColumns = @JoinColumn(name = "chapter_id")
    )
    private List<FileEntity> segments;

    private boolean paid;
    private BigDecimal amountToPay;
}
