package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyToComment implements ICommandHandler<ReplyToCommentCommand> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(ReplyToCommentCommand command) {
        // Найти родительский комментарий
        CommentEntity parentComment = commentRepository.findById(command.getParentCommentId()).orElse(null);
        if (parentComment == null) {
            return CommandResult.failure("Parent comment not found");
        }

        // Создать новый ответный комментарий
        CommentEntity reply = CommentEntity.builder()
                .author(accessPolicy.getCurrentUser())
                .text(command.getContent())
                .parentComment(parentComment)
                .build();

        // Сохранить ответ
        parentComment.getReplies().add(reply);
        commentRepository.save(parentComment); // Сохраняет родительский комментарий вместе с новым ответом

        return CommandResult.success(reply.id);
    }
}
