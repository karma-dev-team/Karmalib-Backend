package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CleanHistory implements ICommandHandler<CleanHistoryCommand> {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public CommandResult handle(CleanHistoryCommand command) {
        historyRepository.deleteByUserId(command.getUserId());
        return CommandResult.success(command.getUserId());
    }
}