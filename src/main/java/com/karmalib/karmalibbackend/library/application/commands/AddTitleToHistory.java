package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.AuthorRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTitleToHistory implements ICommandHandler<AddTitleToHistoryCommand> {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(AddTitleToHistoryCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.failure("Title not found");
        }

        var historyRecord = HistoryEntity.builder()
                .userId(command.getUserId())
                .title(title)
                .viewedAt(LocalDateTime.now())
                .build();

        historyRepository.save(historyRecord);
        return CommandResult.success(historyRecord.getId());
    }
}