package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.HistoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteHistoryRecord implements ICommandHandler<DeleteHistoryRecordCommand> {
    @Autowired
    private HistoryRecordRepository historyRepository;

    @Override
    public CommandResult handle(DeleteHistoryRecordCommand command) {
        var history = historyRepository.findById(command.getHistoryId()).orElse(null);

        if (history == null) {
            return CommandResult.notFound("History record not found", command.getHistoryId());
        }

        historyRepository.delete(history);
        return CommandResult.success(history.id);
    }
}