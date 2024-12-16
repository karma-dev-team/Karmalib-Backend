package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.user.domain.enums.NotificationType;
import com.karmalib.karmalibbackend.user.domain.enums.UserRole;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateNotificationCommand extends BaseCommand {
    private UUID userId = null;
    private UUID groupId = null;
    private UserRole userRole = null; // roles to send
    private String content;  // content of notification, can be link
    private UUID commentId = null;  // used to reference comment.
    private List<InputFileCommand> attachments = null;
    private UUID titleId = null; // title id used for
    private NotificationType type = NotificationType.General;
    private String systemToken = null;
}
