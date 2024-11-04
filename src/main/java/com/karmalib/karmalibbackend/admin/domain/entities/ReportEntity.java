package com.karmalib.karmalibbackend.admin.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reports")
@Getter
@Setter
public class ReportEntity extends BaseEntity {
    private String content;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private UserEntity reportedUser;

    @ManyToOne
    @JoinColumn(name = "reported_title_id")
    private TitleEntity reportedTitle;

    @ManyToOne
    @JoinColumn(name = "reported_chapter_id")
    private ChapterEntity reportedChapter;

    private boolean resolved;
}