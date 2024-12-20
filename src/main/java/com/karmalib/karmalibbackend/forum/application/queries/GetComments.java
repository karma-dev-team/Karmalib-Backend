package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetComments implements IQueryHandler<GetCommentsQuery, List<CommentModel>> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentModel> handle(GetCommentsQuery query) {
        if (query.getPostId() != null) {
            var post = postRepository.findById(query.getPostId()).orElse(null);
            if (post == null) {
                throw new EntityNotFoundException("Не найдено");
            }
            return post.getComments().stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else if (query.getUserId() != null) {
            return commentRepository.findByAuthorId(query.getUserId()).stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else if (query.getTitleId() != null) {
            var title = titleRepository.findById(query.getTitleId()).orElse(null);
            if (title == null) {
                throw new EntityNotFoundException("Не найдено");
            }
            return title.getComments().stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else {
            return List.of();
        }
    }
}