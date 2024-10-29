package com.karmalib.karmalibbackend.library.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_records")
public class HistoryRecordEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity user;

    @ManyToOne
    @JoinColumn(name = "title_id")
    public TitleEntity title;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    public ChapterEntity chapter;

    public LocalDateTime viewedAt;
}
