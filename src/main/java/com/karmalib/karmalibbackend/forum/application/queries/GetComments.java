package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetComments implements IQueryHandler<GetCommentsQuery, List<CommentModel>> {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentModel> handle(GetCommentsQuery query) {
        if (query.getPostId() != null) {
            return commentRepository.findByPostId(query.getPostId()).stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else if (query.getUserId() != null) {
            return commentRepository.findByAuthorId(query.getUserId()).stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else if (query.getTitleId() != null) {
            return commentRepository.findByTitleId(query.getTitleId()).stream()
                    .map(CommentModel::fromEntity)
                    .toList();
        } else {
            return List.of();
        }
    }
}