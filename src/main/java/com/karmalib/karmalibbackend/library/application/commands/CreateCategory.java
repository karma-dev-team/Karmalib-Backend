package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class CreateCategory implements ICommandHandler<CreateCategoryCommand> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CommandResult handle(CreateCategoryCommand command) {
        var category = CategoryEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .build();

        categoryRepository.save(category);
        return CommandResult.success(category.getId());
    }
}