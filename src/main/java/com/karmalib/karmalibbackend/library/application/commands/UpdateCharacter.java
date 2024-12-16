package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCharacter implements ICommandHandler<UpdateCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private SaveFile saveFile;

    @Override
    public CommandResult handle(UpdateCharacterCommand command) {
        var character = characterRepository.findById(command.getCharacterId()).orElse(null);

        if (character == null) {
            return CommandResult.notFound("Character not found", command.getCharacterId());
        }

        if (command.getNewDescription() != null)
            character.setDescription(command.getNewDescription());
        if (command.getNewName() != null)
            character.setName(command.getNewName());
        if (command.getNewAlternativeName() != null)
            character.setAlternativeName(command.getNewAlternativeName());
        if (command.getFile() != null) {
            var file = saveFile.handleInner(command.getFile());
            character.setAvatar(file);
        }

        characterRepository.save(character);

        return CommandResult.success(character.id);
    }
}