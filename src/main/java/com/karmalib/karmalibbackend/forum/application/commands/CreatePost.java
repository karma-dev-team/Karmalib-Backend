package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.admin.infrastructure.repositories.PostRepository;
import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.file.application.queries.GetFilesListQuery;
import com.karmalib.karmalibbackend.file.application.services.FileService;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreatePost implements ICommandHandler<CreatePostCommand> {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    public CommandResult handle(CreatePostCommand command) {
        var results = fileService.saveFiles(command.getFiles());

        List<UUID> ids = results.stream()
                .map(CommandResult::getId) // Получаем String
                .map(UUID::fromString) // Преобразуем в UUID
                .toList();

        var attachments = new HashSet<>(fileRepository.findAllById(ids));

        PostEntity post = PostEntity.builder()
                .status(PostStatus.Waiting)
                .text(command.getText())
                .title(command.getTitle())
                .user(accessPolicy.getCurrentUser())
                .attachments(attachments)
                .build();

        postRepository.save(post);

        return CommandResult.success(post.id);
    }
}
