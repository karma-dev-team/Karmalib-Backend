package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddFriendCommand extends BaseCommand {
    private UUID friendId;
}
