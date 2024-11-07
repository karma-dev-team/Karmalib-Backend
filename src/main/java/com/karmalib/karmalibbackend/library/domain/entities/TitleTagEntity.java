package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "title_tags")
public class TitleTagEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false) // Внешний ключ title_id
    public TitleEntity title;
    private String name;
}
