package com.karmalib.karmalibbackend.common.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class BaseCommand {
    private final String commandId;
    private final Instant timestamp;

    protected BaseCommand() {
        this.commandId = UUID.randomUUID().toString(); // Уникальный идентификатор команды
        this.timestamp = Instant.now(); // Временная метка создания команды
    }

    protected BaseCommand(String commandId, Instant timestamp) {
        this.commandId = commandId != null ? commandId : UUID.randomUUID().toString();
        this.timestamp = timestamp != null ? timestamp : Instant.now();
    }
}
