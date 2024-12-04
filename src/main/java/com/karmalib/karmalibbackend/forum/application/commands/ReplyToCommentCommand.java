package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyToCommentCommand extends BaseCommand {
    private UUID userId;       // ID пользователя, создающего ответ
    private UUID parentCommentId; // ID родительского комментария
    private String content;    // Текст ответа
}