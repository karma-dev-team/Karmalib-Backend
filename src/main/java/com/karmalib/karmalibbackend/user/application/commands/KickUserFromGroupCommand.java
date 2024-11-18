package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;

import java.util.UUID;

@Data
public class KickUserFromGroupCommand extends BaseCommand {
    public UUID groupId;
    public UUID userId;
}
