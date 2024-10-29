package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    public String name;
    public boolean visible;
    public String description;

    @OneToMany(mappedBy = "category")
    public List<TitleEntity> titles;
}