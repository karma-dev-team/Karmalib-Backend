package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;
import java.util.UUID;

@Data
public class RespondToInvitationCommand extends BaseCommand {
    private UUID invitationId;
    private boolean accepted; // true для принятия приглашения, false для отказа
}