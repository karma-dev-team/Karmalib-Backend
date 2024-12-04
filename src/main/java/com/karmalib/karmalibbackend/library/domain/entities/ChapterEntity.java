package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "chapters")
public class ChapterEntity extends BaseEntity {
    private int number;
    private String name;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleEntity title;

    private LocalDateTime publicationDate;
    private boolean paid;
    private BigDecimal amountToPay;

    @OneToMany(mappedBy = "chapter")
    private List<ChapterTranslationEntity> translations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id")
    private List<CommentEntity> comments = new ArrayList<>();
}
