package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.CreatorEntity;
import com.karmalib.karmalibbackend.library.domain.enums.CountryType;
import com.karmalib.karmalibbackend.library.domain.enums.CreatorType;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class CreatorModel extends BaseModel {
    private String name;
    private CreatorType type;
    private List<String> aliases;
    private CountryType country;
    private String description;

    public static CreatorModel fromEntity(CreatorEntity creatorEntity) {
        return CreatorModel.builder()
                .id(creatorEntity.id)
                .name(creatorEntity.getName())
                .type(creatorEntity.getType())
                .aliases(creatorEntity.getAliases())
                .country(creatorEntity.getCountry())
                .description(creatorEntity.getDescription())
                .createdAt(creatorEntity.createdAt)
                .updatedAt(creatorEntity.updatedAt)
                .build();
    }
}
