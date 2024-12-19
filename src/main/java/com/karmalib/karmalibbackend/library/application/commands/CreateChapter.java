package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterTranslationEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterTranslationRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import com.karmalib.karmalibbackend.user.domain.entities.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CreateChapter implements ICommandHandler<CreateChapterCommand> {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterTranslationRepository translationRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private SaveFile saveFile;

    @Override
    public CommandResult handle(CreateChapterCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Не найден тайтл", command.getTitleId());
        }

        // Найти автора по ID пользователя или группы
        AuthorEntity author = null;
        if (command.getAuthorId() != null) {
            author = authorRepository.findByUserId(command.getAuthorId()).orElse(null);
        } else if (command.getGroupId() != null) {
            author = authorRepository.findByGroupId(command.getGroupId()).orElse(null);
        }

        if (author == null ||
                (!title.getTranslators().contains(author) && command.getGroupId() == null)) {
            return CommandResult.forbidden("Автор не имеет прав на добавление перевода");
        }

        if (!author.getIsAllowedToPost()) {
            return CommandResult.forbidden("Вам не положено добавлять главы");
        }

        // Создать новую главу, если ее еще нет
        ChapterEntity chapter = chapterRepository.findByIndexAndTitleId(command.getIndex(), title.id)
                .orElseGet(() -> {
                    var newChapter = ChapterEntity.builder()
                            .title(title)
                            .index(command.getIndex())
                            .name(command.getName())
                            .translations(new ArrayList<>())
                            .build();
                    chapterRepository.save(newChapter);
                    return newChapter;
                });

        // Сохранить сегменты (файлы)
        var savedSegments = command.getSegments().stream()
                .map(saveFile::handleInner)
                .toList();

        // Создать новый перевод главы
        ChapterTranslationEntity translation = ChapterTranslationEntity.builder()
                .chapter(chapter)
                .author(author)
                .publicationDate(LocalDateTime.now())
                .delayedPublicationDate(command.getDelayedPublicationDate())
                .segments(savedSegments)
                .paid(false) // Укажите нужное значение
                .amountToPay(BigDecimal.ZERO) // Укажите нужное значение
                .build();

        translationRepository.save(translation);

        // Добавить перевод к главе
        chapter.getTranslations().add(translation);
        chapterRepository.save(chapter);

        return CommandResult.success(chapter.getId());
    }
}
