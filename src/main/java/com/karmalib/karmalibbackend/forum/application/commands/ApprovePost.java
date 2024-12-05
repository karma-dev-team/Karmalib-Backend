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
public class ApprovePost implements ICommandHandler<ApprovePostCommand> {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private EventDispatcher eventDispatcher;

    public CommandResult handle(ApprovePostCommand command) {
        PostEntity post = postRepository.findById(command.getPostId()).orElse(null);

        if (post == null) {
            return CommandResult.failure("Не найден пост", command.getPostId());
        }

        post.approve(accessPolicy.getCurrentUser());
        eventDispatcher.dispatch(post.getDomainEvents());

        postRepository.save(post);

        return CommandResult.success(command.getPostId());
    }
}
