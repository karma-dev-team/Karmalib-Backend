package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserCommand extends BaseCommand {
    private String username;
    @Email
    private String email;
    private String password;
}
