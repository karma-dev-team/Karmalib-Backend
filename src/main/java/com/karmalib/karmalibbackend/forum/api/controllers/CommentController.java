package com.karmalib.karmalibbackend.forum.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.CustomResponseEntity;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.forum.application.commands.AddCommentaryCommand;
import com.karmalib.karmalibbackend.forum.application.commands.*;
import com.karmalib.karmalibbackend.forum.application.queries.*;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.forum.application.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponseEntity> addComment(@RequestBody AddCommentaryCommand command) {
        CommandResult result = commentService.addComment(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/reply")
    public ResponseEntity<CustomResponseEntity> replyToComment(@RequestBody ReplyToCommentCommand command) {
        CommandResult result = commentService.replyToComment(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/like")
    public ResponseEntity<CustomResponseEntity> likeComment(@RequestBody LikeCommentaryCommand command) {
        CommandResult result = commentService.likeComment(command);
        return RestService.buildResponse(result);
    }

    @PutMapping
    public ResponseEntity<CustomResponseEntity> updateComment(@RequestBody UpdateCommentCommand command) {
        CommandResult result = commentService.updateComment(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/pin")
    public ResponseEntity<CustomResponseEntity> pinComment(@RequestBody PinCommentCommand command) {
        CommandResult result = commentService.pinComment(command);
        return RestService.buildResponse(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommentModel>> getComments(@RequestBody GetCommentsQuery query) {
        List<CommentModel> comments = commentService.getComments(query);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
