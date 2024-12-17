package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookmarkCategory implements ICommandHandler<UpdateBookmarkCategoryCommand> {

    @Autowired
    private BookmarkCategoryRepository categoryRepository;

    @Override
    @Transactional
    public CommandResult handle(UpdateBookmarkCategoryCommand command) {
        // Найти категорию по ID
        var category = categoryRepository.findById(command.getCategoryId())
                .orElse(null);

        if (category == null) {
            return CommandResult.notFound("Категория не найдена", command.getCategoryId());
        }

        // Обновить поля категории
        if (command.getNewName() != null && !command.getNewName().isBlank()) {
            category.setName(command.getNewName());
        }
        if (command.getIsPublic() != null) {
            category.setIsPubliclyVisible(command.getIsPublic());
        }
        if (command.getIsSendNotifications() != null) {
            category.setIsSendNotifications(command.getIsSendNotifications());
        }

        // Сохранить изменения
        categoryRepository.save(category);

        return CommandResult.success(category.id);
    }
}