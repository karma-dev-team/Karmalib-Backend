package com.karmalib.karmalibbackend.user.domain.entities;

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
    public File avatar;

    @OneToMany(mappedBy = "user")
    public List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "user")
    public List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public List<Group> groups;
}
