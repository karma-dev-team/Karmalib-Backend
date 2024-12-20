package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.HistoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleanHistory implements ICommandHandler<CleanHistoryCommand> {
    @Autowired
    private HistoryRecordRepository historyRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(CleanHistoryCommand command) {
        historyRepository.deleteByUserId(accessPolicy.getCurrentUser().id);
        return CommandResult.success(accessPolicy.getCurrentUser().id);
    }
}