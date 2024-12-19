package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CreateCharacter createCharacter;
    private final UpdateCharacter updateCharacter;
    private final DeleteCharacter deleteCharacter;
    private final ApproveCharacter approveCharacter;

    @Autowired
    public CharacterService(
            CreateCharacter createCharacter,
            UpdateCharacter updateCharacter,
            DeleteCharacter deleteCharacter,
            ApproveCharacter approveCharacter
    ) {
        this.createCharacter = createCharacter;
        this.updateCharacter = updateCharacter;
        this.deleteCharacter = deleteCharacter;
        this.approveCharacter = approveCharacter;
    }

    public CommandResult createCharacter(CreateCharacterCommand command) {
        return createCharacter.handle(command);
    }

    public CommandResult updateCharacter(UpdateCharacterCommand command) {
        return updateCharacter.handle(command);
    }

    public CommandResult deleteCharacter(DeleteCharacterCommand command) {
        return deleteCharacter.handle(command);
    }

    public CommandResult approveCharacter(ApproveCharacterCommand command) {
        return approveCharacter.handle(command);
    }
}
