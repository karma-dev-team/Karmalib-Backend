package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CharacterRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveCharacter implements ICommandHandler<ApproveCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public CommandResult handle(ApproveCharacterCommand command) {
        var character = characterRepository.findById(command.getCharacterId()).orElse(null);

        if (character == null) {
            return CommandResult.notFound("Character not found", command.getCharacterId());
        }

        character.setApproved(true);
        characterRepository.save(character);

        return CommandResult.success(character.getId());
    }
}