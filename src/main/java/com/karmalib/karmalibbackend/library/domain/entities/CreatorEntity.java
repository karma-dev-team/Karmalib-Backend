package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.library.domain.enums.CountryType;
import com.karmalib.karmalibbackend.library.domain.enums.CreatorType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatorEntity extends BaseEntity {
    private String name;
    private CreatorType type;
    @ElementCollection(targetClass = String.class)
    private List<String> aliases;
    private CountryType country;
    private String description;
}
