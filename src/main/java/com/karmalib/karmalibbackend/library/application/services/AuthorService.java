package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthor;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthorQuery;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthorsList;
import com.karmalib.karmalibbackend.library.application.queries.GetAuthorsListQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AddAuthorToTitle addAuthorToTitle;
    private final DeleteAuthor deleteAuthor;
    private final UpdateAuthor updateAuthor;
    private final GetAuthor getAuthor;
    private final GetAuthorsList getAuthorsList;

    @Autowired
    public AuthorService(
            AddAuthorToTitle addAuthorToTitle,
            DeleteAuthor deleteAuthor,
            UpdateAuthor updateAuthor,
            GetAuthor getAuthor,
            GetAuthorsList getAuthorsList
    ) {
        this.addAuthorToTitle = addAuthorToTitle;
        this.deleteAuthor = deleteAuthor;
        this.updateAuthor = updateAuthor;
        this.getAuthor = getAuthor;
        this.getAuthorsList = getAuthorsList;
    }

    public CommandResult addAuthorToTitle(AddAuthorToTitleCommand command) {
        return addAuthorToTitle.handle(command);
    }

    public CommandResult deleteAuthor(DeleteAuthorCommand command) {
        return deleteAuthor.handle(command);
    }

    public CommandResult updateAuthor(UpdateAuthorCommand command) {
        return updateAuthor.handle(command);
    }

    public AuthorModel getAuthor(GetAuthorQuery query) {
        return getAuthor.handle(query);
    }

    public List<AuthorModel> getAuthorsList(GetAuthorsListQuery query) {
        return getAuthorsList.handle(query);
    }
}
