package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity @Table(name = "authors")
// record of original author of manga
public class AuthorEntity extends BaseEntity {

}