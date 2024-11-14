package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import lombok.Data;
import java.util.UUID;

@Data
public class DeleteGroupCommand implements ICommand {
    private UUID groupId;
    private UUID userId;
}