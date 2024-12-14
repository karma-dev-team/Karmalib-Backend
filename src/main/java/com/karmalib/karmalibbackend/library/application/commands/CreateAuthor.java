package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CreateAuthor implements ICommandHandler<CreateAuthorCommand> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CommandResult handle(CreateAuthorCommand command) {
        var author = AuthorEntity.builder()
                .name(command.getName())
                .bio(command.getBio())
                .build();

        authorRepository.save(author);
        return CommandResult.success(author.getId());
    }
}
