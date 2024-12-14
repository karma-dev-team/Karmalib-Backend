package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class UpdateBookmarkCategory implements ICommandHandler<UpdateBookmarkCategoryCommand> {
    @Autowired
    private BookmarkCategoryRepository bookmarkCategoryRepository;

    @Override
    public CommandResult handle(UpdateBookmarkCategoryCommand command) {
        var category = bookmarkCategoryRepository.findById(command.getCategoryId()).orElse(null);

        if (category == null) {
            return CommandResult.failure("Category not found");
        }

        category.setName(command.getNewName());
        bookmarkCategoryRepository.save(category);

        return CommandResult.success(category.getId());
    }
}