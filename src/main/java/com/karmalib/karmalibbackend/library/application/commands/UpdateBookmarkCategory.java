package com.karmalib.karmalibbackend.library.application.commands;
import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkCategoryRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookmarkCategory implements ICommandHandler<UpdateBookmarkCategoryCommand> {
    @Autowired
    private BookmarkCategoryRepository bookmarkCategoryRepository;

    @Override
    public CommandResult handle(UpdateBookmarkCategoryCommand command) {
        var category = bookmarkCategoryRepository.findById(command.getCategoryId()).orElse(null);

        if (category == null) {
            return CommandResult.notFound("Category not found", command.getCategoryId());
        }

        if (command.getIsPublic() != null)
            category.setIsPubliclyVisible(command.getIsPublic());
        if (command.getIsSendNotifications() != null)
            category.setIsSendNotifications(command.getIsSendNotifications());
        if (command.getNewName() != null && !command.getNewName().isEmpty())
            category.setName(command.getNewName());
        bookmarkCategoryRepository.save(category);

        return CommandResult.success(category.id);
    }
}