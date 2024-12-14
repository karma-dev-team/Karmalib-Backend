package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteHistoryRecord implements ICommandHandler<DeleteHistoryRecordCommand> {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public CommandResult handle(DeleteHistoryRecordCommand command) {
        var history = historyRepository.findById(command.getHistoryId()).orElse(null);

        if (history == null) {
            return CommandResult.failure("History record not found");
        }

        historyRepository.delete(history);
        return CommandResult.success(history.getId());
    }
}