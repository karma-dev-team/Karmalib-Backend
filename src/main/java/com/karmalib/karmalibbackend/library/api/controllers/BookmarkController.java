package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetBookmarksListQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkModel;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> markAsBookmarked(@RequestBody MarkAsBookmarkedCommand command) {
        CommandResult result = bookmarkService.markAsBookmarked(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<?> deleteBookmarked(@PathVariable UUID bookmarkId) {
        DeleteBookmarkedCommand command = DeleteBookmarkedCommand.builder().bookmarkId(bookmarkId).build();
        CommandResult result = bookmarkService.deleteBookmarked(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createBookmarkCategory(@RequestBody CreateBookmarkCategoryCommand command) {
        CommandResult result = bookmarkService.createBookmarkCategory(command);
        return RestService.buildResponse(result);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateBookmarkCategory(@PathVariable UUID categoryId, @RequestBody UpdateBookmarkCategoryCommand command) {
        command.setCategoryId(categoryId);
        CommandResult result = bookmarkService.updateBookmarkCategory(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteBookmarkCategory(@PathVariable UUID categoryId) {
        DeleteBookmarkCategoryCommand command = DeleteBookmarkCategoryCommand
                .builder()
                .bookmarkCategoryId(categoryId)
                .build();
        CommandResult result = bookmarkService.deleteBookmarkCategory(command);
        return RestService.buildResponse(result);
    }

    // -------- Методы запросов --------

    @GetMapping
    public ResponseEntity<?> getBookmarksList() {
        List<BookmarkModel> bookmarks = bookmarkService.getBookmarksList(
                GetBookmarksListQuery.builder().build());
        return ResponseEntity.ok(bookmarks);
    }
}
