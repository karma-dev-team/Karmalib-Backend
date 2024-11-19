package com.karmalib.karmalibbackend.common.application;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommandResult {
    private Boolean isSuccess;
    private String message;
    private String id;
    private List<String> ids;

    public static CommandResult success(UUID id) {
        return new CommandResult(true, "", id.toString(), null);
    }

    public static CommandResult success() {
        return new CommandResult(true, "", null, null);
    }

    public static CommandResult success(List<UUID> successIds) {
        return new CommandResult(
                true, "", null, successIds.stream().map(UUID::toString).toList());
    }

    public static CommandResult failure(String message) {
        return new CommandResult(false, message, null, null);
    }

    public static CommandResult failure(String message, UUID id) {
        return new CommandResult(false, message, id.toString(), null);
    }

    public static CommandResult failure(String message, String ident) {
        return new CommandResult(false, message, ident, null);
    }

    public static CommandResult failure(String message, List<UUID> failureIds) {
        return new CommandResult(
                false, message, null, failureIds.stream().map(UUID::toString).toList());
    }

    public static CommandResult empty() {
        return new CommandResult(false, "", null, null);
    }

}