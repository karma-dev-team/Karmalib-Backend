package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.domain.entities.GenreEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.domain.enums.ModerationStatus;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.GenreRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleTagRepository;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateTitle implements ICommandHandler<CreateTitleCommand> {
    // человек отправляет на проверку модерации создания облощки тайтла
    // и присуждает исключительные права (во первой) к этому автору для публикации
    // если кто то хочет добавить
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TitleTagRepository titleTagRepository;

    @Autowired
    private SaveFile saveFile;

    @Override
    public CommandResult handle(CreateTitleCommand command) {
        var user = accessPolicy.getCurrentUser();
        List<AuthorEntity> translators = new ArrayList<>();

        if (command.getGroupId() != null) {
            var group = groupRepository.findById(command.getGroupId()).orElse(null);
            if (group == null) {
                return CommandResult.notFound("Группа не найдена", command.getGroupId());
            }
            translators.add(AuthorEntity.builder()
                    .group(group)
                    .build());
        } else if (command.getUserId() != null) {
            translators.add(AuthorEntity.builder()
                    .user(accessPolicy.getCurrentUser())
                    .build());
        } else {
            return CommandResult.badRequest("Не найдено группы и пользователя");
        }

        var file = saveFile.handleInner(command.getCoverImage());

        List<GenreEntity> genres = genreRepository.findAllById(command.getGenreIds());
        List<TitleTagEntity> tags = titleTagRepository.findAllById(command.getTagIds());

        var title = TitleEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .status(command.getStatus())
                .translators(translators)
                .alternateNames(command.getAlternateNames())
                .moderationStatus(ModerationStatus.Waiting)
                .genres(genres)
                .tags(tags)
                .coverImage(file)
                .pgRating(command.getPgRatings())
                .hentai(command.getHentai() != null ? command.getHentai() : false)
                .ronabe(command.getRonabe() != null ? command.getRonabe() : false)
                .ownedBy(accessPolicy.getCurrentUser())
                .build();

        titleRepository.save(title);
        return CommandResult.success(title.id);
    }
}