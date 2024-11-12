package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserCommand implements ICommand {
    private UUID userId;
    private String password = null;
    private String username = null;
    private Boolean isPrivate = null;
    private Boolean isStuff = null;
    private Integer sex = null;  // yes
    private Boolean isNotifyBookmarks = null;
    private String description = null;
}
