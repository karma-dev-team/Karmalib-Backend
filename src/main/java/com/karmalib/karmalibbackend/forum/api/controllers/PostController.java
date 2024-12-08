package com.karmalib.karmalibbackend.forum.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.CustomResponseEntity;
import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.application.commands.*;
import com.karmalib.karmalibbackend.forum.application.queries.*;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.forum.application.queries.models.PostModel;
import com.karmalib.karmalibbackend.forum.application.services.CommentService;
import com.karmalib.karmalibbackend.forum.application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponseEntity> createPost(@RequestBody CreatePostCommand command) {
        CommandResult result = postService.createPost(command);
        CustomResponseEntity response = toCustomResponse(result);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomResponseEntity> updatePost(@RequestBody UpdatePostCommand command) {
        CommandResult result = postService.updatePost(command);
        CustomResponseEntity response = toCustomResponse(result);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<CustomResponseEntity> deletePost(@PathVariable UUID postId,
                                                           @RequestParam(required = false) String reason) {
        // Создаем команду для удаления поста, передаем в неё ID поста и причину
        DeletePostCommand command = new DeletePostCommand(postId, reason);

        // Вызываем сервис для выполнения команды
        CommandResult result = postService.deletePost(command);

        // Преобразуем результат в CustomResponseEntity
        CustomResponseEntity response = toCustomResponse(result);

        // Возвращаем ответ с соответствующим статусом
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/approve")
    public ResponseEntity<CustomResponseEntity> approvePost(@RequestBody ApprovePostCommand command) {
        CommandResult result = postService.approvePost(command);
        CustomResponseEntity response = toCustomResponse(result);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/pin")
    public ResponseEntity<CustomResponseEntity> pinPost(@RequestBody PinPostCommand command) {
        CommandResult result = postService.pinPost(command);
        CustomResponseEntity response = toCustomResponse(result);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostModel>> getPosts(@RequestBody GetPostsListQuery query) {
        List<PostModel> posts = postService.getPostsList(query);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    private CustomResponseEntity toCustomResponse(CommandResult result) {
        return CustomResponseEntity.of(result.getIsSuccess(), UUID.fromString(result.getId()), result.getMessage());
    }
}

