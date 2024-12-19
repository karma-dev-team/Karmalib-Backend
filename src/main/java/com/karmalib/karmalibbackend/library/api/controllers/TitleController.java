package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody CreateTitleCommand command) {
        CommandResult result = titleService.createTitle(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{titleId}")
    public ResponseEntity<?> deleteTitle(@PathVariable UUID titleId) {
        DeleteTitleCommand command = DeleteTitleCommand.builder().titleId(titleId).build();
        CommandResult result = titleService.deleteTitle(command);
        return RestService.buildResponse(result);
    }
}
