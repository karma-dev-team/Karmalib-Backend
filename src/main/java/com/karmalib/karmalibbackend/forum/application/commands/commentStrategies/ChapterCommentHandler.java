package com.karmalib.karmalibbackend.forum.application.commands.commentStrategies;

import com.karmalib.karmalibbackend.admin.infrastructure.repositories.PostRepository;
import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.library.domain.entities.ChapterEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChapterCommentHandler implements CommentTargetHandler {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public boolean canHandle(AddCommentaryCommand command) {
        return command.getChapterId() != null;
    }

    @Override
    public void handleComment(CommentEntity comment, AddCommentaryCommand command) {
        ChapterEntity chapter = chapterRepository.findById(command.getChapterId()).orElse(null);
        if (chapter == null) {
            throw new IllegalArgumentException("Chapter not found");
        }
        chapter.getComments().add(comment);
        chapterRepository.save(chapter);
    }
}