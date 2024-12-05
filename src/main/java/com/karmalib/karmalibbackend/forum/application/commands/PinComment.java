package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.EventDispatcher;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinComment implements ICommandHandler<PinCommentCommand> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private EventDispatcher eventDispatcher;

    public CommandResult handle(PinCommentCommand command) {
        if (!accessPolicy.isModerator()) {
            return CommandResult.failure("Access denied");
        }

        // Найти комментарий
        CommentEntity comment = commentRepository.findById(command.getCommentId()).orElse(null);
        if (comment == null) {
            return CommandResult.failure("Comment not found");
        }

        comment.setPinned(true);

        eventDispatcher.dispatch(comment.getDomainEvents());
        commentRepository.save(comment);


        return CommandResult.success(comment.id);
    }
}
