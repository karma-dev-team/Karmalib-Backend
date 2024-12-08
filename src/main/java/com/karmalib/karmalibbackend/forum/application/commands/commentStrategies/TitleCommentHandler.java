package com.karmalib.karmalibbackend.forum.application.commands.commentStrategies;

import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleCommentHandler implements CommentTargetHandler {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public boolean canHandle(AddCommentaryCommand command) {
        return command.getTitleId() != null;
    }

    @Override
    public void handleComment(CommentEntity comment, AddCommentaryCommand command) {
        TitleEntity title = titleRepository.findById(command.getTitleId()).orElse(null);
        if (title == null) {
            throw new IllegalArgumentException("Title not found");
        }
        title.getComments().add(comment);
        titleRepository.save(title);
    }
}