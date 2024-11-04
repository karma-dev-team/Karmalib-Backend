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
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private String description;
    private boolean isStaff = false;
    private boolean isSuperuser = false;
    private int sex;
    private boolean isPrivate = false;
    private boolean needEmail = false;
    private boolean isNotifyBookmarks = false;

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
    private List<TopicEntity> topics;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "author")
    private List<ChapterTranslationEntity> chapterTranslations;

    @ManyToMany(mappedBy = "users")
    private List<GroupEntity> groups;
}