package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.library.domain.enums.CountryType;
import com.karmalib.karmalibbackend.library.domain.enums.CreatorType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatorEntity extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private CreatorType type;
    @ElementCollection(targetClass = String.class)
    private List<String> aliases;
    private CountryType country;
    private String description;
}
