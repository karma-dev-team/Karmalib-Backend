package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmailCommand extends BaseCommand {
    private UUID userId;
    @Email
    private String email;
}
