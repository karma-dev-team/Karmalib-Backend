package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkAsBookmarked implements ICommandHandler<MarkAsBookmarkedCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public CommandResult handle(MarkAsBookmarkedCommand command) {
        var existingBookmark = bookmarkRepository.findByUserIdAndTitleId(command.getUserId(), command.getTitleId());

        if (existingBookmark.isPresent()) {
            bookmarkRepository.delete(existingBookmark.get());
            return CommandResult.success(command.getTitleId());
        }

        var newBookmark = BookmarkEntity.builder()
                .userId(command.getUserId())
                .titleId(command.getTitleId())
                .build();

        bookmarkRepository.save(newBookmark);
        return CommandResult.success(newBookmark.getId());
    }
}