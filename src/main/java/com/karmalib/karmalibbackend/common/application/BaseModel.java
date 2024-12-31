package com.karmalib.karmalibbackend.common.application;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
public abstract class BaseModel {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}