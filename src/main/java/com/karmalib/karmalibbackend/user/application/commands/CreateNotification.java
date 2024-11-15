package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.common.infrastrcuture.eventDispatcher.IEventDispatcher;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.forum.infrastructure.repositories.CommentRepository;
import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import com.karmalib.karmalibbackend.user.application.services.PasswordHasherService;
import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.MessagingQueue;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.NotificationRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNotification implements ICommandHandler<CreateNotificationCommand> {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private MessagingQueue messagingQueue; // Очередь сообщений для WebSocket

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GroupRepository groupRepository; // Для извлечения пользователей группы
    @Autowired
    private UserRepository userRepository;

    public CommandResult handle(CreateNotificationCommand command) {
        // Верификация прав доступа
        if (!accessPolicy.isAdmin() && !accessPolicy.isSystemRequest(command.getSystemToken())) {
            return CommandResult.failure("Unauthorized request");
        }

        // Проверяем, что только одно из полей userId или groupId заполнено
        if ((command.getUserId() != null && command.getGroupId() != null) ||
                (command.getUserId() == null && command.getGroupId() == null)) {
            return CommandResult.failure("Specify either userId or groupId, not both");
        }

        // Проверяем корректность типа уведомления
        CommandResult validation = validateNotificationType(command);
        if (!validation.getIsSuccess()) {
            return validation;
        }

        TitleEntity title = titleRepository.findById(command.getTitleId()).orElse(null);
        if (title == null && command.getTitleId() != null) {
            return CommandResult.failure("Title not found");
        }

        CommentEntity comment = commentRepository.findById(command.getCommentId()).orElse(null);
        if (comment == null && command.getCommentId() != null) {
            return CommandResult.failure("Comment not found");
        }

        UserEntity user = userRepository.findById(command.getUserId()).orElse(null);
        if (user == null && command.getUserId() != null) {
            return CommandResult.failure("User not found");
        }

        // Создаем объект уведомления с использованием @Builder
        NotificationEntity notification = NotificationEntity.builder()
                .content(command.getContent())
                .type(command.getType())
                .titleEntity(title)
                .comment(comment)
//                .attachments(command.getAttachments())  # TODO: add file saving
                .recipient(user)
                .build();

        // Сохраняем уведомление в базе
        notification = notificationRepository.save(notification);

        // Отправляем уведомление через WebSocket
        messagingQueue.send(notification);

        return CommandResult.success(notification.id);
    }

    private CommandResult validateNotificationType(CreateNotificationCommand command) {
        switch (command.getType()) {
            case General:
                break;
            case Social:
                if (command.getCommentId() == null) {
                    return CommandResult.failure("Forum notifications require a commentId");
                }
                break;
            case Updates:
                if (command.getTitleId() == null) {
                    return CommandResult.failure("Update notifications require a titleId");
                }
                break;
            case Important:
                if (command.getContent() == null || command.getContent().isBlank()) {
                    return CommandResult.failure("Important notifications require content");
                }
                break;
            default:
                return CommandResult.failure("Unsupported notification type");
        }
        return CommandResult.success();
    }
}
