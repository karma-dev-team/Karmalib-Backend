package com.karmalib.karmalibbackend.library.application.commands;

@Service
public class DeleteChapter implements ICommandHandler<DeleteChapterCommand> {
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public CommandResult handle(DeleteChapterCommand command) {
        var chapter = chapterRepository.findById(command.getChapterId()).orElse(null);

        if (chapter == null) {
            return CommandResult.failure("Chapter not found");
        }

        chapterRepository.delete(chapter);
        return CommandResult.success(chapter.getId());
    }
}