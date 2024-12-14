package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteCharacter implements ICommandHandler<DeleteCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public CommandResult handle(DeleteCharacterCommand command) {
        var character = characterRepository.findById(command.getCharacterId()).orElse(null);

        if (character == null) {
            return CommandResult.failure("Character not found");
        }

        characterRepository.delete(character);
        return CommandResult.success(character.getId());
    }
}