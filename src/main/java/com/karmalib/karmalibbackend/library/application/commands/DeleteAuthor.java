package com.karmalib.karmalibbackend.library.application.commands;


@Service
public class DeleteAuthor implements ICommandHandler<DeleteAuthorCommand> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CommandResult handle(DeleteAuthorCommand command) {
        var author = authorRepository.findById(command.getAuthorId()).orElse(null);

        if (author == null) {
            return CommandResult.failure("Author not found");
        }

        authorRepository.delete(author);
        return CommandResult.success(author.getId());
    }
}