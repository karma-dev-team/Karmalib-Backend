package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;

import java.util.UUID;

@Data
public class DeleteAccountCommand extends BaseCommand {
    private UUID userId;
    private String reason;
    private String token = null;
}
