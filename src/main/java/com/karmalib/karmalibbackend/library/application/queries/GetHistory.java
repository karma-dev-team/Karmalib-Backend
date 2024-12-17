package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.library.application.queries.models.HistoryRecordModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.HistoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetHistory implements IQueryHandler<GetHistoryQuery, List<HistoryRecordModel>> {
    @Autowired
    private HistoryRecordRepository historyRepository;

    @Override
    public List<HistoryRecordModel> handle(GetHistoryQuery query) {
        var history = historyRepository.findAllByUserId(query.getUserId());
        return history.stream().map(HistoryRecordModel::fromEntity).toList();
    }
}