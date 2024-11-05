package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.ICommand;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CreateUserCommand implements ICommand {
    private String username;
    @Email
    private String email;
    private String password;
}
