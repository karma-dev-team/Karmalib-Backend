package com.karmalib.karmalibbackend.admin.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CommentEntity extends BaseEntity {
    @Id
    public UUID Id;
}
