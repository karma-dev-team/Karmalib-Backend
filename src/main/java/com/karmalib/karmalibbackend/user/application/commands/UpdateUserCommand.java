package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateUserCommand extends BaseCommand {
    private UUID userId;
    @Builder.Default
    private String password = null;
    @Builder.Default
    private String username = null;
    @Builder.Default
    private Boolean isPrivate = null;
    @Builder.Default
    private Boolean isStuff = null;
    @Builder.Default
    private Integer sex = null;  // yes
    @Builder.Default
    private Boolean isNotifyBookmarks = null;
    @Builder.Default
    private String description = null;
}
