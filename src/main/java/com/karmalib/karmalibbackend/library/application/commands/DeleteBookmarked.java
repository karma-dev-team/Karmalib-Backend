package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteBookmarked implements ICommandHandler<DeleteBookmarkedCommand> {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public CommandResult handle(DeleteBookmarkedCommand command) {
        var bookmark = bookmarkRepository.findById(command.getBookmarkId()).orElse(null);

        if (bookmark == null) {
            return CommandResult.failure("Bookmark not found");
        }

        bookmarkRepository.delete(bookmark);
        return CommandResult.success(bookmark.getId());
    }
}