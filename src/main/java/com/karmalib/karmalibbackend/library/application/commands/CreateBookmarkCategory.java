package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkCategoryEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookmarkCategory implements ICommandHandler<CreateBookmarkCategoryCommand> {
    @Autowired
    private BookmarkCategoryRepository categoryRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(CreateBookmarkCategoryCommand command) {
        var category = BookmarkCategoryEntity.builder()
                .user(accessPolicy.getCurrentUser())
                .name(command.getCategoryName())
                .build();

        categoryRepository.save(category);
        return CommandResult.success(category.id);
    }
}