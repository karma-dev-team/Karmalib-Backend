package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class UpdateCharacter implements ICommandHandler<UpdateCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public CommandResult handle(UpdateCharacterCommand command) {
        var character = characterRepository.findById(command.getCharacterId()).orElse(null);

        if (character == null) {
            return CommandResult.failure("Character not found");
        }

        character.setName(command.getNewName());
        character.setDescription(command.getNewDescription());
        characterRepository.save(character);

        return CommandResult.success(character.getId());
    }
}