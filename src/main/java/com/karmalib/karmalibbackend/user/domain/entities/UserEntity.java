package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    public String username;
    public String email;
    public String password;
    public LocalDateTime registrationDate;
    public String role;
    public String description;
    public String tag;
    public int gender;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    public FileEntity avatar;

    @OneToMany(mappedBy = "user")
    public List<BookmarkEntity> bookmarks;

    @OneToMany(mappedBy = "user")
    public List<PostEntity> posts;

    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public List<GroupEntity> groups;
}
