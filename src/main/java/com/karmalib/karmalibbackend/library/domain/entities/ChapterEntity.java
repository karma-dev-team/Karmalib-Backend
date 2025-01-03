package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterEntity extends BaseEntity {
    private int number;
    private String name;
    private String chapter;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleEntity title;

    @OneToMany(mappedBy = "chapter")
    private List<ChapterTranslationEntity> translations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id")
    private List<CommentEntity> comments = new ArrayList<>();
    private int index; 
}
