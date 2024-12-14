package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class ReviewTitle implements ICommandHandler<ReviewTitleCommand> {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public CommandResult handle(ReviewTitleCommand command) {
        var review = ReviewEntity.builder()
                .titleId(command.getTitleId())
                .userId(command.getUserId())
                .review(command.getReview())
                .build();

        reviewRepository.save(review);
        return CommandResult.success(review.getId());
    }
}