package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.application.commands.commentStrategies.CommentTargetHandler;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddCommentary implements ICommandHandler<AddCommentaryCommand> {

    @Autowired
    private List<CommentTargetHandler> handlers;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(AddCommentaryCommand command) {
        CommentEntity comment = CommentEntity.builder()
                .author(accessPolicy.getCurrentUser())
                .text(command.getText())
                .build();

        // Выбираем подходящий обработчик
        for (CommentTargetHandler handler : handlers) {
            if (handler.canHandle(command)) {
                handler.handleComment(comment, command);
                commentRepository.save(comment);
                return CommandResult.success(comment.id);
            }
        }

        return CommandResult.failure("No valid target ID provided for the comment");
    }
}