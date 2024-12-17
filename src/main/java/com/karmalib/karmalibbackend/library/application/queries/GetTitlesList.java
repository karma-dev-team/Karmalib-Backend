package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.TitleModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetTitlesList implements IQueryHandler<GetTitlesListQuery, List<TitleModel>> {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<TitleModel> handle(final GetTitlesListQuery query) {
        // Выполнение запроса с использованием фильтров
        var titles = titleRepository.findByFilters(
                query.getKeyword(),
                query.getTags() != null ? query.getTags() : Collections.emptyList(),
                query.getExcludeTags() != null ? query.getExcludeTags() : Collections.emptyList(),
                query.getGenres() != null ? query.getGenres() : Collections.emptyList(),
                query.getExcludeGenres() != null ? query.getExcludeGenres() : Collections.emptyList(),
                query.getPgRatings(),
                query.getTitleStatus(),
                query.getTranslationStatus(),
                query.getStartDate(),
                query.getEndDate(),
                query.getMinReviews(),
                query.getMaxReviews()
        );

        // Преобразование из TitleEntity в TitleModel
        return titles.stream()
                .map(TitleModel::fromEntity)
                .toList();
    }
}
