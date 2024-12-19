package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.domain.entities.GenreEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGenre implements ICommandHandler<CreateGenreCommand> {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public CommandResult handle(CreateGenreCommand command) {
        var genre = genreRepository.findByName(command.getGenreName()).orElse(null);

        if (genre != null) {
            return CommandResult.notFound("Genre with that name is already exists", command.getGenreName());
        }

        var newGenre = GenreEntity.builder()
                .name(command.getGenreName())
                .visible(command.getVisible())
                .description(command.getDescription())
                .build();

        genreRepository.save(newGenre);

        return CommandResult.success(newGenre.id);
    }
}