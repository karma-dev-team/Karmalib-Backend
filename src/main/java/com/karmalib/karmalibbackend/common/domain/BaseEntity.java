package com.karmalib.karmalibbackend.common.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    public UUID Id;

    @Column(nullable = false, updatable = false)
    public LocalDateTime CreatedAt;

    @Column(nullable = false)
    public LocalDateTime UpdatedAt;

    @PrePersist
    protected void onCreate() {
        CreatedAt = LocalDateTime.now();   // Устанавливается текущая дата и время при создании записи
        UpdatedAt = CreatedAt;             // Устанавливается тоже значение для созданной записи
    }

    @PreUpdate
    protected void onUpdate() {
        UpdatedAt = LocalDateTime.now();   // Обновляется дата и время при каждом изменении записи
    }
}
