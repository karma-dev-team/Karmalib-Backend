package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthorQuery;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthorsListQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.AuthorModel;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> addAuthorToTitle(@RequestBody AddAuthorToTitleCommand command) {
        CommandResult result = authorService.addAuthorToTitle(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable UUID authorId) {
        DeleteAuthorCommand command = DeleteAuthorCommand.builder().authorId(authorId).build();
        CommandResult result = authorService.deleteAuthor(command);
        return RestService.buildResponse(result);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable UUID authorId, @RequestBody UpdateAuthorCommand command) {
        command.setAuthorId(authorId);
        CommandResult result = authorService.updateAuthor(command);
        return RestService.buildResponse(result);
    }

    // -------- Методы запросов --------

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable UUID authorId) {
        GetAuthorQuery query = GetAuthorQuery.builder().authorId(authorId).build();
        AuthorModel author = authorService.getAuthor(query);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public ResponseEntity<?> getAuthorsList(Boolean allowed) {
        List<AuthorModel> authors = authorService.getAuthorsList(
                GetAuthorsListQuery.builder()
                        .isAllowedToPost(allowed).build());
        return ResponseEntity.ok(authors);
    }
}
