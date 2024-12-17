package com.karmalib.karmalibbackend.common.domain;

import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.BaseEvent;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue
    public UUID id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

    @Transient
    private List<BaseEvent> domainEvents = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    // Custom hashCode method based on the entity ID
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
