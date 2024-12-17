package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.GenreModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGenresList implements IQueryHandler<GetGenresListQuery, List<GenreModel>> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreModel> handle(GetGenresListQuery query) {
        var genres = query.getVisibleOnly() != null && query.getVisibleOnly()
                ? genreRepository.findAllByVisible(query.getVisibleOnly())
                : genreRepository.findAll();
        return genres.stream().map(GenreModel::fromEntity).toList();
    }
}
