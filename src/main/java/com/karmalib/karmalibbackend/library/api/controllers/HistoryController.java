package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> addTitleToHistory(@RequestBody AddTitleToHistoryCommand command) {
        CommandResult result = historyService.addTitleToHistory(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{historyId}")
    public ResponseEntity<?> deleteHistoryRecord(@PathVariable UUID historyId) {
        DeleteHistoryRecordCommand command = DeleteHistoryRecordCommand.builder().historyId(historyId).build();
        CommandResult result = historyService.deleteHistoryRecord(command);
        return RestService.buildResponse(result);
    }

    @PostMapping("/clean")
    public ResponseEntity<?> cleanHistory() {
        CleanHistoryCommand command = new CleanHistoryCommand();
        CommandResult result = historyService.cleanHistory(command);
        return RestService.buildResponse(result);
    }
}
