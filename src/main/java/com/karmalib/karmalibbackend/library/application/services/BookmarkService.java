package com.karmalib.karmalibbackend.library.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.library.application.commands.*;
import com.karmalib.karmalibbackend.library.application.queries.GetBookmarksCategoryList;
import com.karmalib.karmalibbackend.library.application.queries.GetBookmarksCategoryListQuery;
import com.karmalib.karmalibbackend.library.application.queries.GetBookmarksList;
import com.karmalib.karmalibbackend.library.application.queries.GetBookmarksListQuery;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkCategoryModel;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final MarkAsBookmarked markAsBookmarked;
    private final DeleteBookmarked deleteBookmarked;
    private final CreateBookmarkCategory createBookmarkCategory;
    private final UpdateBookmarkCategory updateBookmarkCategory;
    private final DeleteBookmarkCategory deleteBookmarkCategory;
    private final GetBookmarksList getBookmarksList;
    private final GetBookmarksCategoryList getBookmarksCategoryList;

    @Autowired
    public BookmarkService(
            MarkAsBookmarked markAsBookmarked,
            DeleteBookmarked deleteBookmarked,
            CreateBookmarkCategory createBookmarkCategory,
            UpdateBookmarkCategory updateBookmarkCategory,
            DeleteBookmarkCategory deleteBookmarkCategory,
            GetBookmarksList getBookmarksList,
            GetBookmarksCategoryList getBookmarksCategoryList
    ) {
        this.markAsBookmarked = markAsBookmarked;
        this.deleteBookmarked = deleteBookmarked;
        this.createBookmarkCategory = createBookmarkCategory;
        this.updateBookmarkCategory = updateBookmarkCategory;
        this.deleteBookmarkCategory = deleteBookmarkCategory;
        this.getBookmarksList = getBookmarksList;
        this.getBookmarksCategoryList = getBookmarksCategoryList;
    }

    public CommandResult markAsBookmarked(MarkAsBookmarkedCommand command) {
        return markAsBookmarked.handle(command);
    }

    public CommandResult deleteBookmarked(DeleteBookmarkedCommand command) {
        return deleteBookmarked.handle(command);
    }

    public CommandResult createBookmarkCategory(CreateBookmarkCategoryCommand command) {
        return createBookmarkCategory.handle(command);
    }

    public CommandResult updateBookmarkCategory(UpdateBookmarkCategoryCommand command) {
        return updateBookmarkCategory.handle(command);
    }

    public CommandResult deleteBookmarkCategory(DeleteBookmarkCategoryCommand command) {
        return deleteBookmarkCategory.handle(command);
    }

    public List<BookmarkModel> getBookmarksList(GetBookmarksListQuery query) {
        return getBookmarksList.handle(query);
    }

    public List<BookmarkCategoryModel> getBookmarksCategoryList(GetBookmarksCategoryListQuery query) {
        return getBookmarksCategoryList.handle(query);
    }
}
