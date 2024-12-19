package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> createChapter(@RequestBody CreateChapterCommand command) {
        CommandResult result = chapterService.createChapter(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable UUID chapterId) {
        DeleteChapterCommand command = DeleteChapterCommand.builder().chapterId(chapterId).build();
        CommandResult result = chapterService.deleteChapter(command);
        return RestService.buildResponse(result);
    }
}
