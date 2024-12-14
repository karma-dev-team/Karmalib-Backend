package com.karmalib.karmalibbackend.library.application.commands;


@Service
public class UpdateTitle implements ICommandHandler<UpdateTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(UpdateTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        title.setTitle(command.getNewTitle());
        title.setDescription(command.getNewDescription());
        titleRepository.save(title);

        return CommandResult.success(title.getId());
    }
}