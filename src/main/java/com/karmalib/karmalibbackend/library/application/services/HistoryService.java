package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetHistory;
import com.karmalib.karmalibbackend.library.application.queries.GetHistoryQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.HistoryRecordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private final AddTitleToHistory addTitleToHistory;
    private final DeleteHistoryRecord deleteHistoryRecord;
    private final CleanHistory cleanHistory;
    private final GetHistory getHistory;

    @Autowired
    public HistoryService(
            AddTitleToHistory addTitleToHistory,
            DeleteHistoryRecord deleteHistoryRecord,
            CleanHistory cleanHistory,
            GetHistory getHistory
    ) {
        this.addTitleToHistory = addTitleToHistory;
        this.deleteHistoryRecord = deleteHistoryRecord;
        this.cleanHistory = cleanHistory;
        this.getHistory = getHistory;
    }

    public CommandResult addTitleToHistory(AddTitleToHistoryCommand command) {
        return addTitleToHistory.handle(command);
    }

    public CommandResult deleteHistoryRecord(DeleteHistoryRecordCommand command) {
        return deleteHistoryRecord.handle(command);
    }

    public CommandResult cleanHistory(CleanHistoryCommand command) {
        return cleanHistory.handle(command);
    }

    public List<HistoryRecordModel> getHistory(GetHistoryQuery query) {
        return getHistory.handle(query);
    }
}
