package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetTags implements IQueryHandler<GetTagsQuery, List<String>> {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<String> handle(GetTagsQuery query) {
        var title = titleRepository.findById(query.getTitleId())
                .orElseThrow(() -> new EntityNotFoundException("Title not found"));

        return title.getTags().stream()
                .map(TitleTagEntity::getName)
                .toList();
    }
}