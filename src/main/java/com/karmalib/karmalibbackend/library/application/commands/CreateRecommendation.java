package com.karmalib.karmalibbackend.library.application.commands;


@Service
public class CreateRecommendation implements ICommandHandler<CreateRecommendationCommand> {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public CommandResult handle(CreateRecommendationCommand command) {
        var titles = titleRepository.findAllById(command.getTitleIds());

        var recommendation = RecommendationEntity.builder()
                .name(command.getName())
                .titles(titles)
                .build();

        recommendationRepository.save(recommendation);
        return CommandResult.success(recommendation.getId());
    }
}