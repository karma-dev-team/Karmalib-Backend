package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.admin.infrastructure.repositories.PostRepository;
import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeCommentary implements ICommandHandler<LikeCommentaryCommand> {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(LikeCommentaryCommand command) {
        var comment = commentRepository.findById(command.getCommentId()).orElse(null);
        if (comment == null) {
            return CommandResult.failure("No comment was found", command.getCommentId());
        }

        if (comment.hasReaction(accessPolicy.getCurrentUser(), ReactionType.LIKE)) {
            comment.removeReaction(accessPolicy.getCurrentUser(), ReactionType.LIKE);
        } else {
            comment.addReaction(accessPolicy.getCurrentUser(), ReactionType.LIKE);
        }

        commentRepository.save(comment);

        return CommandResult.success(comment.id);
    }
}
