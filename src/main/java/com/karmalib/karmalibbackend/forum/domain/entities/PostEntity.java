package com.karmalib.karmalibbackend.forum.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostStatus;
import com.karmalib.karmalibbackend.forum.domain.events.PostApproved;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private List<ReactionEntity> dislikes;
    private int dislikesCount = dislikes.size();
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<ReactionEntity> likes;
    private int likesCount = likes.size();

    private PostStatus status = PostStatus.Waiting;

    @NonNull
    private String text;
    @NonNull
    private String title;

    private UUID approvedById;

    @ManyToMany
    @JoinTable(
            name = "post_attachments",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<FileEntity> attachments;

    private boolean hidden;

    public void approve(UserEntity user) {
        status = PostStatus.Approved;
        addDomainEvent(new PostApproved(this.id, user.id));
    }
}
