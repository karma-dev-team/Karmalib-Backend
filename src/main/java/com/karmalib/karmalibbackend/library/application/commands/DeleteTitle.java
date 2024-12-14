package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteTitle implements ICommandHandler<DeleteTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(DeleteTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        titleRepository.delete(title);
        return CommandResult.success(title.getId());
    }
}