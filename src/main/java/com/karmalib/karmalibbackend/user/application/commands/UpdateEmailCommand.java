package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmailCommand implements ICommand {
    private UUID userId;
    @Email
    private String email;
    private String token = "";
}
