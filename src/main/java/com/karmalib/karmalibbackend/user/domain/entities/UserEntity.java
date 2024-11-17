package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.admin.domain.entities.NewsEntity;
import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.TopicEntity;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterTranslationEntity;
import com.karmalib.karmalibbackend.library.domain.entities.ReviewEntity;
import com.karmalib.karmalibbackend.user.domain.enums.UserRole;
import com.karmalib.karmalibbackend.user.domain.events.FriendAdded;
import com.karmalib.karmalibbackend.user.domain.events.FriendRemoved;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private String hashedPassword;
    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now();
    private String description;
    @Builder.Default
    private boolean isStaff = false;
    @Builder.Default
    private boolean isSuperuser = false;
    private int sex;
    @Builder.Default
    private boolean isPrivate = false;
    @Builder.Default
    private boolean needEmail = false;
    @Builder.Default
    private boolean isNotifyBookmarks = false;
    @Builder.Default
    private boolean isBanned = false;
    @Builder.Default
    private boolean isSuspended = false;
    @Builder.Default
    private boolean isOnline = true;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private Set<UserRole> roles;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private FileEntity avatar;

    @OneToMany(mappedBy = "user")
    private List<NotificationEntity> notifications;

    @OneToMany(mappedBy = "author")
    private List<NewsEntity> news;

    @OneToMany(mappedBy = "user")
    private List<BookmarkEntity> bookmarks;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private List<GroupInvitationEntity> invitations;

    @OneToMany(mappedBy = "author")
    private List<ChapterTranslationEntity> chapterTranslations;

    @ManyToMany(mappedBy = "users")
    private List<GroupEntity> groups;

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<UserEntity> friends;

    // Метод для добавления друга
    public void addFriend(UserEntity friend) {
        this.friends.add(friend);
        friend.friends.add(this);
        addDomainEvent(new FriendAdded(friend.id, this.id));
    }

    // Метод для удаления друга
    public void removeFriend(UserEntity friend) {
        this.friends.remove(friend);
        friend.friends.remove(this);
        addDomainEvent(new FriendRemoved(friend.id, this.id));
    }
}