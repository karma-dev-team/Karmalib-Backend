package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.AuthorModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAuthorsList implements IQueryHandler<GetAuthorsListQuery, List<AuthorModel>> {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorModel> handle(final GetAuthorsListQuery query) {
        List<AuthorEntity> authors;
        authors = authorRepository.findAll();
        if (query.getIsAllowedToPost() != null) {
            authors = authorRepository.findAllByIsAllowedToPost(query.getIsAllowedToPost());
        }
        return authors.stream().map(AuthorModel::fromEntity).toList();
    }
}