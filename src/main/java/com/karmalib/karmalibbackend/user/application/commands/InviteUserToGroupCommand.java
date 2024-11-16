package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;
import java.util.UUID;

@Data
public class InviteUserToGroupCommand extends BaseCommand {
    private UUID groupId;
    private UUID invitedUserId; // ID пользователя, которого нужно пригласить
}
