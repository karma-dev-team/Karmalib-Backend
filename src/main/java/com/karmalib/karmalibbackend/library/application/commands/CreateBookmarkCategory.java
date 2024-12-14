package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CreateBookmarkCategory implements ICommandHandler<CreateBookmarkCategoryCommand> {
    @Autowired
    private BookmarkCategoryRepository categoryRepository;

    @Override
    public CommandResult handle(CreateBookmarkCategoryCommand command) {
        var category = BookmarkCategoryEntity.builder()
                .userId(command.getUserId())
                .name(command.getCategoryName())
                .build();

        categoryRepository.save(category);
        return CommandResult.success(category.getId());
    }
}