package com.karmalib.karmalibbackend.common.presentation;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomResponseEntity {
    private boolean success;
    private UUID id;
    private String description;

    // Конструктор
    public CustomResponseEntity(boolean success, UUID id, String description) {
        this.success = success;
        this.id = id;
        this.description = description;
    }

    // Статический фабричный метод для удобства
    public static CustomResponseEntity of(boolean success, UUID id, String description) {
        return new CustomResponseEntity(success, id, description);
    }
}