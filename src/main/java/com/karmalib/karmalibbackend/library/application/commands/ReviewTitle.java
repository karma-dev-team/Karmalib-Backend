package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.ReviewEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.ReviewRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewTitle implements ICommandHandler<ReviewTitleCommand> {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(ReviewTitleCommand command) {
        var title = titleRepository.findById(command.getTitleId()).orElse(null);
        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }
        var author = accessPolicy.getCurrentUser();

        var review = ReviewEntity.builder()
                .user(author)
                .rating(command.getRating())
                .content(command.getContent())
                .build();

        title.getReviews().add(review);

        reviewRepository.save(review);
        return CommandResult.success(review.id);
    }
}