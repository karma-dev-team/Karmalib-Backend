package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authors")
@Data
public class AuthorEntity extends BaseEntity {

    @ManyToOne
    private GroupEntity group;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private TitleEntity title;

    private static AuthorEntity create(GroupEntity group) {
        var author = new AuthorEntity();

        author.setGroup(group);

        return author;
    }

    private static AuthorEntity create(UserEntity user) {
        var author = new AuthorEntity();

        author.setUser(user);

        return author;
    }

}