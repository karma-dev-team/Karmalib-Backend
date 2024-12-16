package com.karmalib.karmalibbackend.library.application.commands;


import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookmark implements ICommandHandler<UpdateBookmarkCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public CommandResult handle(UpdateBookmarkCommand command) {
        var bookmark = bookmarkRepository.findById(command.getBookmarkId()).orElse(null);

        if (bookmark == null) {
            return CommandResult.notFound("Bookmark not found", command.getBookmarkId());
        }

        bookmark.setCategory(command.getNewCategory());
        bookmarkRepository.save(bookmark);

        return CommandResult.success(bookmark.getId());
    }
}