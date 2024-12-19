package com.karmalib.karmalibbackend.common.presentation;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Data
public class CustomResponseEntity {
    private boolean success;
    private UUID id;
    private HttpStatus status;
    private String message;

    // Конструктор
    public CustomResponseEntity(boolean success, UUID id, HttpStatus status, String message) {
        this.success = success;
        this.id = id;
        this.status = status;
        this.message = message;
    }

    // Статический фабричный метод для удобства
    public static CustomResponseEntity of(boolean success, UUID id, HttpStatus status, String message) {
        return new CustomResponseEntity(success, id, status, message);
    }
}