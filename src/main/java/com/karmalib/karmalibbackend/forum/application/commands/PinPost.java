package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.EventDispatcher;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinPost implements ICommandHandler<PinPostCommand> {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccessPolicy accessPolicy;
    public CommandResult handle(PinPostCommand command) {
        if (!accessPolicy.isAdmin()) {
            return CommandResult.failure("Access denied", command.getPostId());
        }
        PostEntity post = postRepository.findById(command.getPostId()).orElse(null);
        if (post == null) {
            return CommandResult.failure("Пост не найден", command.getPostId());
        }

        post.pin(!command.getUnpin());

        return CommandResult.success(command.getPostId());
    }
}
