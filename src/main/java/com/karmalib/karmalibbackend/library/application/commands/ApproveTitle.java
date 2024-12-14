package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class ApproveTitle implements ICommandHandler<ApproveTitleCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(ApproveTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        title.setApproved(true);
        titleRepository.save(title);

        return CommandResult.success(title.getId());
    }
}