package com.karmalib.karmalibbackend.user.application.queries.results;


import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.forum.application.queries.models.CommentModel;
import com.karmalib.karmalibbackend.library.application.queries.models.TitleModel;
import com.karmalib.karmalibbackend.user.domain.entities.NotificationEntity;
import com.karmalib.karmalibbackend.user.domain.enums.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotificationModel extends BaseModel {
    private String content;
    private String title;
    private NotificationType type;
    private boolean read;

    private UserModel recipient;   // Получатель уведомления
    private TitleModel titleEntity; // Ссылка на Title (если есть)
    private CommentModel comment;   // Ссылка на комментарий (если есть)

    public static NotificationModel fromEntity(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }

        return NotificationModel.builder()
                .id(entity.id) // Если BaseModel содержит id
                .createdAt(entity.createdAt) // Если BaseModel содержит createdAt
                .updatedAt(entity.updatedAt) // Если BaseModel содержит updatedAt
                .content(entity.getContent())
                .title(entity.getTitle())
                .type(entity.getType())
                .read(entity.isRead())
                .recipient(UserModel.fromEntity(entity.getRecipient())) // Маппинг получателя
                .titleEntity(TitleModel.fromEntity(entity.getTitleEntity())) // Маппинг Title
                .comment(CommentModel.fromEntity(entity.getComment())) // Маппинг комментария
                .build();
    }
}