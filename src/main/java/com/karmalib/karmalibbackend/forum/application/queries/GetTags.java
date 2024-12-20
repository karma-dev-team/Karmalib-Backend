package com.karmalib.karmalibbackend.forum.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.forum.domain.entities.PostTagEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetTags implements IQueryHandler<GetTagsQuery, List<String>> {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<String> handle(GetTagsQuery query) {
        var tags = tagRepository.findAll();
        List<String> tagsList = new ArrayList<>();

        for (var tag : tags) {
            tagsList.add(tag.getName());
        }

        return tagsList;
    }
}