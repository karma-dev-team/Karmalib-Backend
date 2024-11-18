package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmark_categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkCategoryEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
    private String name;
    private Boolean isPubliclyVisible = true;
    private Boolean isSendNotifications = true;
}
