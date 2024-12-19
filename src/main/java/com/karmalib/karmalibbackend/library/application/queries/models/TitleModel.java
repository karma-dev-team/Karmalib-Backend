package com.karmalib.karmalibbackend.library.application.queries.models;

import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.library.domain.entities.GenreEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.domain.enums.ModerationStatus;
import com.karmalib.karmalibbackend.library.domain.enums.PgRatings;
import com.karmalib.karmalibbackend.library.domain.enums.TitleStatus;
import com.karmalib.karmalibbackend.library.domain.enums.TranslationStatus;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuperBuilder
public class TitleModel extends BaseModel {
    private String name;
    private String description;
    private TitleStatus status;
    private TranslationStatus translationStatus;
    private List<String> alternateNames;
    private LocalDateTime releaseDate;
    private PgRatings pgRating;
    private boolean hentai;
    private boolean ronabe;
    private ModerationStatus moderationStatus;

    private FileModel coverImage;     // Обложка (без циклической зависимости)
    private List<CreatorModel> creators;    // ID оригинального автора
    private List<String> tags;        // Список ID тегов
    private List<AuthorModel> translators;     // Список ID авторов
    private List<String> genres;

    public static TitleModel fromEntity(TitleEntity entity) {
        if (entity == null) {
            return null;
        }

        return TitleModel.builder()
                .id(entity.id) // Публичное поле из BaseEntity
                .createdAt(entity.createdAt) // Публичное поле из BaseEntity
                .updatedAt(entity.updatedAt) // Публичное поле из BaseEntity
                .name(entity.getName()) // Приватное поле, используем геттер
                .description(entity.getDescription()) // Приватное поле, используем геттер
                .status(entity.getStatus()) // Приватное поле, используем геттер
                .translationStatus(entity.getTranslationStatus()) // Приватное поле, используем геттер
                .alternateNames(entity.getAlternateNames()) // Приватное поле, используем геттер
                .releaseDate(entity.getReleaseDate()) // Приватное поле, используем геттер
                .pgRating(entity.getPgRating()) // Приватное поле, используем геттер
                .hentai(entity.isHentai()) // Приватное поле, используем геттер
                .ronabe(entity.isRonabe()) // Приватное поле, используем геттер
                .moderationStatus(entity.getModerationStatus()) // Приватное поле, используем геттер
                .coverImage(FileModel.fromEntity(entity.getCoverImage())) // Преобразование обложки
                .creators(
                        entity.getCreators() == null ? null :
                                entity.getCreators().stream()
                                        .map(CreatorModel::fromEntity) // Преобразование списка создателей
                                        .toList()
                )
                .genres(
                        entity.getGenres() == null ? null :
                                entity.getGenres().stream()
                                        .map(GenreEntity::getName) // Преобразование списка тегов
                                        .toList()
                )
                .tags(
                        entity.getTags() == null ? null :
                                entity.getTags().stream()
                                        .map(TitleTagEntity::getName) // Преобразование списка тегов
                                        .toList()
                )
                .translators(
                        entity.getTranslators() == null ? null :
                                entity.getTranslators().stream()
                                        .map(AuthorModel::fromEntity) // Преобразование списка авторов
                                        .toList()
                )
                .build();
    }
}