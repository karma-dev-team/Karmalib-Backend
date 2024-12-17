package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.BookmarkEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkAsBookmarked implements ICommandHandler<MarkAsBookmarkedCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(MarkAsBookmarkedCommand command) {
        var existingBookmark = bookmarkRepository.findByUserIdAndTitleId(command.getUserId(), command.getTitleId());

        if (existingBookmark.isPresent()) {
            bookmarkRepository.delete(existingBookmark.get());
            return CommandResult.success(command.getTitleId());
        }

        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }

        var newBookmark = BookmarkEntity.builder()
                .user(accessPolicy.getCurrentUser())
                .title(title)
                .build();

        bookmarkRepository.save(newBookmark);
        return CommandResult.success(newBookmark.id);
    }
}