package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public class TitleTagModel extends BaseModel {
    private String name;
    private String slug;

    public static TitleTagModel fromEntity(TitleTagEntity titleTagEntity) {
        if (titleTagEntity == null) {
            return null;
        }

        return TitleTagModel.builder()
                .id(titleTagEntity.id)
                .createdAt(titleTagEntity.createdAt)
                .updatedAt(titleTagEntity.updatedAt)
                .name(titleTagEntity.getName())
                .slug(titleTagEntity.getSlug())
                .build();
    }

}