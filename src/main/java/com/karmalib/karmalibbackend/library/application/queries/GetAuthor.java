package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.AuthorModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthor implements IQueryHandler<GetAuthorQuery, AuthorModel> {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorModel handle(final GetAuthorQuery query) {
        var author = authorRepository.findById(query.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return AuthorModel.fromEntity(author);
    }
}