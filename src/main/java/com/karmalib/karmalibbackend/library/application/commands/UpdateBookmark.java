package com.karmalib.karmalibbackend.library.application.commands;


@Service
public class UpdateBookmark implements ICommandHandler<UpdateBookmarkCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public CommandResult handle(UpdateBookmarkCommand command) {
        var bookmark = bookmarkRepository.findById(command.getBookmarkId()).orElse(null);

        if (bookmark == null) {
            return CommandResult.failure("Bookmark not found");
        }

        bookmark.setCategory(command.getNewCategory());
        bookmarkRepository.save(bookmark);

        return CommandResult.success(bookmark.getId());
    }
}