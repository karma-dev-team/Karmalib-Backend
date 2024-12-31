package com.karmalib.karmalibbackend.common.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class BaseQuery {
    @JsonIgnore
    @Schema(hidden = true)
    private String queryId;  // Уникальный идентификатор запроса
    @JsonIgnore
    @Schema(hidden = true)
    private Instant timestamp; // Временная метка создания запроса

    protected BaseQuery() {
        this.queryId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
    }

    protected BaseQuery(String queryId, Instant timestamp) {
        this.queryId = queryId != null ? queryId : UUID.randomUUID().toString();
        this.timestamp = timestamp != null ? timestamp : Instant.now();
    }
}
