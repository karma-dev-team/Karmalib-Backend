package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostTagType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post_tags")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug; // Для URL-адресов

    private String description;

    @Enumerated(EnumType.STRING)
    private PostTagType type;

    private int usageCount;

    @Builder.Default
    private boolean isVisible = true;

    private String color;

    @ManyToMany
    @JoinTable(
            name = "related_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "related_tag_id")
    )
    private Set<TagEntity> relatedTags = new HashSet<>();
}