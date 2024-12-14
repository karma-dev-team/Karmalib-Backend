package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CreateTitle implements ICommandHandler<CreateTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(CreateTitleCommand command) {
        var title = TitleEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .language(command.getLanguage())
                .build();

        titleRepository.save(title);
        return CommandResult.success(title.getId());
    }
}