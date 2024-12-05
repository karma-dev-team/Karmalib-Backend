package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateComment implements ICommandHandler<UpdateCommentCommand> {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(UpdateCommentCommand command) {
        var comment = commentRepository.findById(command.getCommentId()).orElse(null);

        if (comment == null) {
            return CommandResult.failure("No comment found", command.getCommentId());
        }

        if (!accessPolicy.isUserSelf(comment.getAuthor().id)) {
            return CommandResult.failure("Access denied");
        }

        if (command.getText() != null) {
            comment.setText(command.getText());
        }

        if (command.getIsSpoiler() != null) {
            comment.setIsSpoiler(command.getIsSpoiler());
        }

        return CommandResult.success(command.getCommentId());
    }
}
