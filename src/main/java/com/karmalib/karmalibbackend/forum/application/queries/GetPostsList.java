package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.application.queries.models.PostModel;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsList implements IQueryHandler<GetPostsListQuery, List<PostModel>> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostModel> handle(GetPostsListQuery query) {
        var posts = postRepository.findAll(); // Можно добавить фильтры, если требуется
        return posts.stream()
                .map(PostModel::fromEntity)
                .toList();
    }
}
