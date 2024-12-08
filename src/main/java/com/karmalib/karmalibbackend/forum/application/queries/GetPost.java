package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.application.queries.models.PostModel;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPost implements IQueryHandler<GetPostQuery, PostModel> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostModel handle(GetPostQuery query) {
        var post = postRepository.findById(query.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        return PostModel.fromEntity(post);
    }
}