package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class UpdateAuthor implements ICommandHandler<UpdateAuthorCommand> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CommandResult handle(UpdateAuthorCommand command) {
        var author = authorRepository.findById(command.getAuthorId()).orElse(null);

        if (author == null) {
            return CommandResult.failure("Author not found");
        }

        author.setName(command.getName());
        author.setBio(command.getBio());
        authorRepository.save(author);

        return CommandResult.success(author.getId());
    }
}