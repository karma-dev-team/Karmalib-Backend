package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactToPost implements ICommandHandler<ReactToPostCommand> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    public CommandResult handle(ReactToPostCommand command) {
        var post = postRepository.findById(command.getPostId()).orElse(null);
        if (post == null) {
            return CommandResult.failure("No post was found", command.getPostId());
        }

        var currentUser = accessPolicy.getCurrentUser();

        if (post.hasReaction(currentUser, command.getReactionType())) {
            post.removeReaction(currentUser, command.getReactionType());
        } else {
            post.addReaction(currentUser, command.getReactionType());
        }

        postRepository.save(post);

        return CommandResult.success(post.id);
    }
}