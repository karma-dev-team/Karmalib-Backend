package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.application.queries.models.PostModel;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
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
        List<PostEntity> posts;

        if (query.getTags() != null && !query.getTags().isEmpty()) {
            // Фильтрация по тегам
            posts = query.isRecentPopular()
                    ? postRepository.findByTagsOrderByLikesCountDesc(query.getTags())
                    : postRepository.findByTagsOrderByCreatedAtDesc(query.getTags());
        } else {
            // Без тегов, просто фильтрация по популярности или дате
            posts = query.isRecentPopular()
                    ? postRepository.findAllByOrderByLikesCountDesc()
                    : postRepository.findAllByOrderByCreatedAtDesc();
        }

        return posts.stream()
                .map(PostModel::fromEntity)
                .toList();
    }
}
