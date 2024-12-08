package com.karmalib.karmalibbackend.forum.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.forum.application.commands.*;
import com.karmalib.karmalibbackend.forum.application.queries.GetPost;
import com.karmalib.karmalibbackend.forum.application.queries.GetPostQuery;
import com.karmalib.karmalibbackend.forum.application.queries.GetPostsList;
import com.karmalib.karmalibbackend.forum.application.queries.GetPostsListQuery;
import com.karmalib.karmalibbackend.forum.application.queries.models.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final CreatePost createPost;
    private final UpdatePost updatePost;
    private final DeletePost deletePost;
    private final PinPost pinPost;
    private final ApprovePost approvePost;
    private final GetPost getPost;
    private final GetPostsList getPostsList;

    @Autowired
    public PostService(
            CreatePost createPost,
            UpdatePost updatePost,
            DeletePost deletePost,
            PinPost pinPost,
            ApprovePost approvePost,
            GetPost getPost,
            GetPostsList getPostsList
    ) {
        this.createPost = createPost;
        this.updatePost = updatePost;
        this.deletePost = deletePost;
        this.pinPost = pinPost;
        this.approvePost = approvePost;
        this.getPost = getPost;
        this.getPostsList = getPostsList;
    }

    public CommandResult createPost(CreatePostCommand command) {
        return createPost.handle(command);
    }

    public CommandResult updatePost(UpdatePostCommand command) {
        return updatePost.handle(command);
    }

    public CommandResult deletePost(DeletePostCommand command) {
        return deletePost.handle(command);
    }

    public CommandResult pinPost(PinPostCommand command) {
        return pinPost.handle(command);
    }

    public CommandResult approvePost(ApprovePostCommand command) {
        return approvePost.handle(command);
    }

    public PostModel getPost(GetPostQuery query) {
        return getPost.handle(query);
    }

    public List<PostModel> getPostsList(GetPostsListQuery query) {
        return getPostsList.handle(query);
    }
}
