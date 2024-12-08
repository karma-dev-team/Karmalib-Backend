package com.karmalib.karmalibbackend.forum.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.forum.application.commands.*;
import com.karmalib.karmalibbackend.forum.application.queries.GetComments;
import com.karmalib.karmalibbackend.forum.application.queries.GetCommentsQuery;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final AddCommentary addCommentary;
    private final ReplyToComment replyToComment;
    private final LikeCommentary likeCommentary;
    private final UpdateComment updateComment;
    private final PinComment pinComment;
    private final GetComments getComments;

    @Autowired
    public CommentService(
            AddCommentary addCommentary,
            ReplyToComment replyToComment,
            LikeCommentary likeCommentary,
            UpdateComment updateComment,
            PinComment pinComment,
            GetComments getComments
    ) {
        this.addCommentary = addCommentary;
        this.replyToComment = replyToComment;
        this.likeCommentary = likeCommentary;
        this.updateComment = updateComment;
        this.pinComment = pinComment;
        this.getComments = getComments;
    }

    public CommandResult addComment(AddCommentaryCommand command) {
        return addCommentary.handle(command);
    }

    public CommandResult replyToComment(ReplyToCommentCommand command) {
        return replyToComment.handle(command);
    }

    public CommandResult likeComment(LikeCommentaryCommand command) {
        return likeCommentary.handle(command);
    }

    public CommandResult updateComment(UpdateCommentCommand command) {
        return updateComment.handle(command);
    }

    public CommandResult pinComment(PinCommentCommand command) {
        return pinComment.handle(command);
    }

    public List<CommentModel> getComments(GetCommentsQuery query) {
        return getComments.handle(query);
    }
}
