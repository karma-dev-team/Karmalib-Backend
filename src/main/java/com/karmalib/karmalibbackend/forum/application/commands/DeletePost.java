package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.EventDispatcher;
import com.karmalib.karmalibbackend.forum.domain.events.PostDeleted;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePost implements ICommandHandler<DeletePostCommand> {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EventDispatcher eventDispatcher;

    public CommandResult handle(DeletePostCommand command) {
        var post = postRepository.findById(command.getPostId()).orElse(null);

        if (post == null) {
            return CommandResult.failure("Пост не найден", command.getPostId());
        }

        eventDispatcher.dispatch(new PostDeleted(command.getPostId(), command.getReason(), post.getUser().id));
        postRepository.delete(post);

        return CommandResult.success(command.getPostId());
    }
}
