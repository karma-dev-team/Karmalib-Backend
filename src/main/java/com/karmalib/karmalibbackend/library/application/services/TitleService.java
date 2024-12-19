package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetTitle;
import com.karmalib.karmalibbackend.library.application.queries.GetTitleQuery;
import com.karmalib.karmalibbackend.library.application.queries.GetTitlesList;
import com.karmalib.karmalibbackend.library.application.queries.GetTitlesListQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.TitleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    private final CreateTitle createTitle;
    private final UpdateTitle updateTitle;
    private final DeleteTitle deleteTitle;
    private final ApproveTitle approveTitle;
    private final ReviewTitle reviewTitle;
    private final GetTitle getTitle;
    private final GetTitlesList getTitlesList;

    @Autowired
    public TitleService(
            CreateTitle createTitle,
            UpdateTitle updateTitle,
            DeleteTitle deleteTitle,
            ApproveTitle approveTitle,
            ReviewTitle reviewTitle,
            GetTitle getTitle,
            GetTitlesList getTitlesList
    ) {
        this.createTitle = createTitle;
        this.updateTitle = updateTitle;
        this.deleteTitle = deleteTitle;
        this.approveTitle = approveTitle;
        this.reviewTitle = reviewTitle;
        this.getTitle = getTitle;
        this.getTitlesList = getTitlesList;
    }

    public CommandResult createTitle(CreateTitleCommand command) {
        return createTitle.handle(command);
    }

    public CommandResult updateTitle(UpdateTitleCommand command) {
        return updateTitle.handle(command);
    }

    public CommandResult deleteTitle(DeleteTitleCommand command) {
        return deleteTitle.handle(command);
    }

    public CommandResult approveTitle(ApproveTitleCommand command) {
        return approveTitle.handle(command);
    }

    public CommandResult reviewTitle(ReviewTitleCommand command) {
        return reviewTitle.handle(command);
    }

    public TitleModel getTitle(GetTitleQuery query) {
        return getTitle.handle(query);
    }

    public List<TitleModel> getTitlesList(GetTitlesListQuery query) {
        return getTitlesList.handle(query);
    }
}
