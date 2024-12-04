package com.karmalib.karmalibbackend.forum.application.commands.commentStrategies;

import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;

public interface CommentTargetHandler {
    boolean canHandle(AddCommentaryCommand command);
    void handleComment(CommentEntity comment, AddCommentaryCommand command);
}