package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteChapter implements ICommandHandler<DeleteChapterCommand> {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(DeleteChapterCommand command) {
        var chapter = chapterRepository.findById(command.getChapterId()).orElse(null);
        if (!accessPolicy.isAdmin()) {
            return CommandResult.forbidden("Не админ");
        }
        if (chapter == null) {
            return CommandResult.notFound("Chapter not found", command.getChapterId());
        }

        chapterRepository.delete(chapter);
        return CommandResult.success(chapter.id);
    }
}