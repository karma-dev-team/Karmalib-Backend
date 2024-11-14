package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import lombok.Data;
import java.util.UUID;

@Data
public class RespondToInvitationCommand implements ICommand {
    private UUID invitationId;
    private boolean accepted; // true для принятия приглашения, false для отказа
}