package com.karmalib.karmalibbackend.common.application;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommandResult {
    private Boolean isSuccess;
    private String message;
    private String id;

    public static CommandResult success(UUID id) {
        return new CommandResult(true, "", id.toString());
    }

    public static CommandResult success() {
        return new CommandResult(true, "", null);
    }

    public static CommandResult failure(String message) {
        return new CommandResult(false, message, null);
    }

    public static CommandResult failure(String message, UUID id) {
        return new CommandResult(false, message, id.toString());
    }

    public static CommandResult failure(String message, String ident) {
        return new CommandResult(false, message, ident);
    }

    public static CommandResult empty() {
        return new CommandResult(false, "", null);
    }

}