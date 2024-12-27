package com.karmalib.karmalibbackend.common.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class BaseCommand {
    @Schema(hidden = true)
    @JsonIgnore
    private final String commandId;
    @JsonIgnore
    @Schema(hidden = true)
    private final LocalDateTime timestamp;

    protected BaseCommand() {
        this.commandId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    protected BaseCommand(String commandId, LocalDateTime timestamp) {
        this.commandId = commandId != null ? commandId : UUID.randomUUID().toString();
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }
}
