package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteAccountCommand extends BaseCommand {
    private UUID userId;
    private String reason;
    @Builder.Default
    private String token = null;
}
