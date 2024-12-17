package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookmarkCategory implements ICommandHandler<DeleteBookmarkCategoryCommand> {

    @Autowired
    private BookmarkCategoryRepository categoryRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    @Transactional
    public CommandResult handle(DeleteBookmarkCategoryCommand command) {
        var category = categoryRepository.findById(command.getBookmarkCategoryId())
                .orElse(null);
        if (category == null) {
            return CommandResult.notFound("Категория закладок не найдена", command.getBookmarkCategoryId());
        }
        if (accessPolicy.getCurrentUser().id != category.getUser().id) {
            return CommandResult.forbidden("Не тот человек");
        }
        // Проверка существования категории


        // Проверка наличия связанных закладок
        boolean hasBookmarks = bookmarkRepository.existsByCategoryId(command.getBookmarkCategoryId());
        if (hasBookmarks) {
            return CommandResult.conflict("Нельзя удалить категорию которая имеет закладки.");
        }

        // Удаление категории
        categoryRepository.delete(category);

        return CommandResult.success(command.getBookmarkCategoryId());
    }
}