package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.TitleModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTitle implements IQueryHandler<GetTitleQuery, TitleModel> {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public TitleModel handle(GetTitleQuery query) {
        var title = titleRepository.findById(query.getTitleId())
                .orElseThrow(() -> new EntityNotFoundException("Title not found"));
        return TitleModel.fromEntity(title);
    }
}