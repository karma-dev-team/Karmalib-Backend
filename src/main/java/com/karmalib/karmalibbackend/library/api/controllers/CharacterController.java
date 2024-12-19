package com.karmalib.karmalibbackend.library.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    // -------- Командные методы --------

    @PostMapping
    public ResponseEntity<?> createCharacter(@RequestBody CreateCharacterCommand command) {
        CommandResult result = characterService.createCharacter(command);
        return RestService.buildResponse(result);
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<?> updateCharacter(@PathVariable UUID characterId, @RequestBody UpdateCharacterCommand command) {
        command.setCharacterId(characterId);
        CommandResult result = characterService.updateCharacter(command);
        return RestService.buildResponse(result);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<?> deleteCharacter(@PathVariable UUID characterId) {
        DeleteCharacterCommand command = DeleteCharacterCommand.builder().characterId(characterId).build();
        CommandResult result = characterService.deleteCharacter(command);
        return RestService.buildResponse(result);
    }
}
