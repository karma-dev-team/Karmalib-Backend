package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetChapter;
import com.karmalib.karmalibbackend.library.application.queries.GetChapterQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.ChapterModel;
import com.karmalib.karmalibbackend.library.application.queries.models.GetChapterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    private final CreateChapter createChapter;
    private final DeleteChapter deleteChapter;
    private final GetChapter getChapter;

    @Autowired
    public ChapterService(
            CreateChapter createChapter,
            DeleteChapter deleteChapter,
            GetChapter getChapter
    ) {
        this.createChapter = createChapter;
        this.deleteChapter = deleteChapter;
        this.getChapter = getChapter;
    }

    public CommandResult createChapter(CreateChapterCommand command) {
        return createChapter.handle(command);
    }

    public CommandResult deleteChapter(DeleteChapterCommand command) {
        return deleteChapter.handle(command);
    }

    public GetChapterResponse getChapter(GetChapterQuery query) {
        return getChapter.handle(query);
    }
}
