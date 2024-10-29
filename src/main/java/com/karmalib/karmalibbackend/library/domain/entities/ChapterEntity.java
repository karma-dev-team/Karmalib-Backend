package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chapters")
public class ChapterEntity extends BaseEntity {

    public int number;
    public String name;

    @ManyToOne
    @JoinColumn(name = "title_id")
    public TitleEntity title;

    public LocalDateTime publicationDate;
    public boolean paid;
    public BigDecimal amountToPay;

    @OneToMany(mappedBy = "chapter")
    public List<ChapterTranslationEntity> translations;
}
