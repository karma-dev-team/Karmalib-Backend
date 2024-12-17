package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TranslationStatus;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetTitlesListQuery extends BaseQuery {
    @Nullable
    private String keyword;
    @Nullable
    private List<String> tags;
    @Nullable
    private List<String> excludeTags;
    @Nullable
    private List<String> genres;
    @Nullable
    private List<String> excludeGenres;
    @Nullable
    private PgRatings pgRatings;
    @Nullable
    private TitleStatus titleStatus;
    @Nullable
    private TranslationStatus translationStatus;
    @Nullable
    private LocalDateTime startDate;
    @Nullable
    private LocalDateTime endDate;
    private int minReviews;
    private int maxReviews;
}