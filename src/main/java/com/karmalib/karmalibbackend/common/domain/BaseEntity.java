package com.karmalib.karmalibbackend.common.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    public UUID id;

    @Column(nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false)
    public LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();   // Устанавливается текущая дата и время при создании записи
        updatedAt = createdAt;             // Устанавливается тоже значение для созданной записи
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();   // Обновляется дата и время при каждом изменении записи
    }
}
