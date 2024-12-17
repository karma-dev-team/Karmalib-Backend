package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.HistoryRecordEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.HistoryRecordRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddTitleToHistory implements ICommandHandler<AddTitleToHistoryCommand> {
    @Autowired
    private HistoryRecordRepository historyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(AddTitleToHistoryCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }

        var historyRecord = HistoryRecordEntity.builder()
                .user(accessPolicy.getCurrentUser())
                .title(title)
                .viewedAt(LocalDateTime.now())
                .build();

        historyRepository.save(historyRecord);
        return CommandResult.success(historyRecord.id);
    }
}