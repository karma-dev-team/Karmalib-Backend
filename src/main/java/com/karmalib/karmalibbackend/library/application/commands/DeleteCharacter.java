package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCharacter implements ICommandHandler<DeleteCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(DeleteCharacterCommand command) {
        var character = characterRepository.findById(command.getCharacterId()).orElse(null);

        if (character == null) {
            return CommandResult.notFound("Персонаж не найден", command.getCharacterId());
        }


        if (!accessPolicy.isUserSelf(character.getAuthor().id) || !accessPolicy.isModerator()) {
            return CommandResult.forbidden("");
        }

        characterRepository.delete(character);
        return CommandResult.success(character.id);
    }
}