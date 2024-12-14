package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CreateCharacter implements ICommandHandler<CreateCharacterCommand> {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(CreateCharacterCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        var character = CharacterEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .title(title)
                .build();

        characterRepository.save(character);
        return CommandResult.success(character.getId());
    }
}