package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.services.FileService;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UpdatePost implements ICommandHandler<UpdatePostCommand> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public CommandResult handle(UpdatePostCommand command) {
        // Найти пост по ID
        var post = postRepository.findById(command.getPostId()).orElse(null);
        if (post == null) {
            return CommandResult.failure("Post not found", command.getPostId());
        }

        // Проверить права доступа: пользователь может обновлять только свои посты
        if (!accessPolicy.isUserSelf(post.getUser().id)) {
            return CommandResult.failure("Access denied");
        }

        // Обновление текста
        if (command.getText() != null) {
            post.setText(command.getText());
        }

        // Обновление статуса "hidden" (скрыт)
        if (command.getHidden() != null) {
            post.setHidden(command.getHidden());
        }
        if (command.getAttachments() != null && !command.getAttachments().isEmpty()) {
            var results = new HashSet<>(fileService.saveFiles(command.getAttachments()));

            List<UUID> ids = results.stream()
                    .map(CommandResult::getId)
                    .map(UUID::fromString)
                    .toList();

            var attachments = new HashSet<>(fileRepository.findAllById(ids));

            post.setAttachments(attachments);
        }

        // Сохранение обновленного поста
        postRepository.save(post);

        return CommandResult.success(post.id);
    }
}
