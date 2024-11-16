package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CreateUserCommand extends BaseCommand {
    private String username;
    @Email
    private String email;
    private String password;
}
