package com.karmalib.karmalibbackend.forum.application.commands.commentStrategies;

import com.karmalib.karmalibbackend.admin.infrastructure.repositories.PostRepository;
import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCommentHandler implements CommentTargetHandler {

    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean canHandle(AddCommentaryCommand command) {
        return command.getPostId() != null;
    }

    @Override
    public void handleComment(CommentEntity comment, AddCommentaryCommand command) {
        PostEntity post = postRepository.findById(command.getPostId()).orElse(null);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
        post.getComments().add(comment);
        postRepository.save(post);
    }
}