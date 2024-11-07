package com.karmalib.karmalibbackend.common.domain;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Transient
    private List<BaseEvent> domainEvents = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();   // Устанавливается текущая дата и время при создании записи
        updatedAt = createdAt;             // Устанавливается тоже значение для созданной записи
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();   // Обновляется дата и время при каждом изменении записи
    }

    public void addDomainEvent(BaseEvent event) {
        domainEvents.add(event);
    }

    public List<BaseEvent> getDomainEvents() {
        clearDomainEvents();
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
