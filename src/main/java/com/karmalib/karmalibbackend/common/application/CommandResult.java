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
    private Optional<UUID> id;

    public static CommandResult success(UUID id) {
        return new CommandResult(true, "", Optional.of(id));
    }

    public static CommandResult success() {
        return new CommandResult(true, "", Optional.empty());
    }

    public static CommandResult failure(String message) {
        return new CommandResult(false, message, null);
    }

    public static CommandResult failure(String message, UUID id) {
        return new CommandResult(false, message, Optional.of(id));
    }
}