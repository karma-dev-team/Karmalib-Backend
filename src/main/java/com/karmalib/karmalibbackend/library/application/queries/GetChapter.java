package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.ChapterModel;
import com.karmalib.karmalibbackend.library.application.queries.models.GetChapterResponse;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterTranslationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetChapter implements IQueryHandler<GetChapterQuery, GetChapterResponse> {
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public GetChapterResponse handle(GetChapterQuery query) {
        var chapterOptional = chapterRepository.findById(query.getChapterId());

        if (chapterOptional.isEmpty()) {
            throw new EntityNotFoundException("Chapter not found with ID: " + query.getChapterId());
        }

        var chapter = chapterOptional.get();

        // Фильтрация переводов по ID автора
        var translationOptional = chapter.getTranslations().stream()
                .filter(translation -> translation.getAuthor() != null
                        && translation.getAuthor().getId().equals(query.getAuthorId()))
                .findFirst();

        if (translationOptional.isEmpty()) {
            throw new EntityNotFoundException("Translation not found for author ID: " + query.getAuthorId());
        }

        // Преобразование в GetChapterResponse
        return GetChapterResponse.fromEntity(chapter, translationOptional.get());
    }
}
