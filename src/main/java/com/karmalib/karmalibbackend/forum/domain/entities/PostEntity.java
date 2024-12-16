package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostStatus;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.forum.domain.events.PostApproved;
import com.karmalib.karmalibbackend.forum.domain.events.PostPinned;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity @Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<CommentEntity> comments;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<ReactionEntity> reactions = new ArrayList<>();
    private int dislikesCount = 0;
    private int likesCount = 0;

    private PostStatus status = PostStatus.Waiting;
    private Boolean pinned;

    @NonNull
    private String text;
    @NonNull
    private String title;

    private UUID approvedById;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<PostTagEntity> tags;

    @ManyToMany
    @JoinTable(
            name = "post_attachments",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<FileEntity> attachments;

    public boolean hasReaction(UserEntity user, ReactionType type) {
        return reactions.stream()
                .anyMatch(reaction -> reaction.getUser().id.equals(user.id) && reaction.getType() == type);
    }

    @Builder.Default
    private boolean hidden = false;

    public void approve(UserEntity user) {
        status = PostStatus.Approved;
        addDomainEvent(new PostApproved(this.id, user.id));
    }

    public void pin(boolean pin) {
        pinned = pin;
        addDomainEvent(new PostPinned(this.id, this.getUser().id));
    }

    // Добавление реакции
    public boolean addReaction(UserEntity user, ReactionType type) {
        if (hasReaction(user, type)) {
            return false;
        }

        // Добавляем новую реакцию
        ReactionEntity newReaction = ReactionEntity.builder()
                .user(user)
                .post(this)
                .type(type)
                .build();

        if (ReactionType.LIKE == type) {
            likesCount++;
        } else {
            dislikesCount++;
        }

        reactions.add(newReaction);

        return true; 
    }

    // Удаление реакции
    public void removeReaction(UserEntity user, ReactionType type) {
        // Ищем реакцию для удаления
        ReactionEntity reactionToRemove = reactions.stream()
                .filter(reaction -> reaction.getUser().id.equals(user.id) && reaction.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User hasn't added this reaction to the comment"));
        if (ReactionType.LIKE == type) {
            likesCount--;
        } else {
            dislikesCount--;
        }

        // Удаляем реакцию
        reactions.remove(reactionToRemove);
    }
}
