package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.application.queries.models.BookmarkCategoryModel;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBookmarksCategoryList implements IQueryHandler<GetBookmarksCategoryListQuery, List<BookmarkCategoryModel>> {

    @Autowired
    private BookmarkCategoryRepository bookmarkCategoryRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public List<BookmarkCategoryModel> handle(GetBookmarksCategoryListQuery query) {
        var categories = bookmarkCategoryRepository.findAllByUserId(accessPolicy.getCurrentUser().id);
        return categories.stream().map(BookmarkCategoryModel::fromEntity).toList();
    }
}