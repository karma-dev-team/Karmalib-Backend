package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;
import java.util.UUID;

@Data
public class GiveOwnershipOfGroupCommand extends BaseCommand {
    private UUID groupId;
    private UUID newOwnerId;
}