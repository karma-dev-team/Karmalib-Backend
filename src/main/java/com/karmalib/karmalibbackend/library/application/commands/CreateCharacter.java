package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.domain.entities.CharacterEntity;
import com.karmalib.karmalibbackend.library.domain.enums.CharacterStatus;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.CharacterRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCharacter implements ICommandHandler<CreateCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private SaveFile saveFile;

    public CommandResult handle(CreateCharacterCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Title not found", command.getTitleId());
        }

        var file = saveFile.handleInner(command.getAvatar());

        var character = CharacterEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .alternativeName(command.getAlternativeName())
                .author(accessPolicy.getCurrentUser())
                .status(CharacterStatus.Waiting)
                .avatar(file)
                .build();

        title.getCharacters().add(character);
        characterRepository.save(character);
        return CommandResult.success(character.id);
    }
}