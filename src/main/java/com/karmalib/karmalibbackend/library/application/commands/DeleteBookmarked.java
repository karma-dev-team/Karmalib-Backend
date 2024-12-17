package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookmarked implements ICommandHandler<DeleteBookmarkedCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public CommandResult handle(DeleteBookmarkedCommand command) {
        var bookmark = bookmarkRepository.findById(command.getBookmarkId()).orElse(null);

        if (bookmark == null) {
            return CommandResult.notFound("Закладка не найдена", command.getBookmarkId());
        }

        bookmarkRepository.delete(bookmark);
        return CommandResult.success(bookmark.id);
    }
}