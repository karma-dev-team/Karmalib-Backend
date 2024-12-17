package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkCategoryModel;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBookmarksList implements IQueryHandler<GetBookmarksListQuery, List<BookmarkModel>> {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public List<BookmarkModel> handle(GetBookmarksListQuery query) {
        var bookmarks = bookmarkRepository.findAllByUserId(accessPolicy.getCurrentUser().id);
        return bookmarks.stream().map(BookmarkModel::fromEntity).toList();
    }
}