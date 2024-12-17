package com.karmalib.karmalibbackend.common.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommandResult {
    private String message;
    private String id;
    private List<String> ids;
    private HttpStatus status;

    public Boolean getIsSuccess() {
        return status == HttpStatus.OK;
    }

    // Успех: создание объекта
    public static CommandResult success(UUID id) {
        return new CommandResult("", id.toString(), null, HttpStatus.OK);
    }

    // Успех: без данных
    public static CommandResult success() {
        return new CommandResult("", null, null, HttpStatus.OK);
    }

    // Успех: список объектов
    public static CommandResult success(List<UUID> successIds) {
        return new CommandResult(
                "", null, successIds.stream().map(UUID::toString).toList(), HttpStatus.OK);
    }

    // Ошибка: не найдено (один объект)
    public static CommandResult notFound(String message, UUID id) {
        return new CommandResult(
                message, id.toString(), null, HttpStatus.NOT_FOUND);
    }

    // Ошибка: не найдено (список объектов)
    public static CommandResult notFound(String message, List<UUID> ids) {
        return new CommandResult(
                message, null, ids.stream().map(UUID::toString).toList(), HttpStatus.NOT_FOUND);
    }

    // Ошибка: плохой запрос
    public static CommandResult badRequest(String message) {
        return new CommandResult(
                message, null, null, HttpStatus.BAD_REQUEST);
    }

    // Ошибка: нарушение инвариантов
    public static CommandResult unprocessableEntity(String message) {
        return new CommandResult(
                message, null, null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Ошибка: нарушение инвариантов с указанием объекта
    public static CommandResult unprocessableEntity(String message, UUID id) {
        return new CommandResult(
                message, id.toString(), null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Ошибка: внутренний сбой сервера
    public static CommandResult internalServerError(String message) {
        return new CommandResult(
                message, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static CommandResult forbidden(String message) {
        return new CommandResult(message, null, null, HttpStatus.FORBIDDEN);
    }

    // Пустой результат
    public static CommandResult empty() {
        return new CommandResult("", null, null, HttpStatus.NO_CONTENT);
    }

    public static CommandResult conflict(String message) {
        return new CommandResult(message, null, null, HttpStatus.CONFLICT);
    }

    // ----- Методы для обратной совместимости -----

    // failure без ID
    @Deprecated
    public static CommandResult failure(String message) {
        return new CommandResult(
                message, null, null, HttpStatus.BAD_REQUEST); // По умолчанию Bad Request
    }

    // failure с UUID
    @Deprecated
    public static CommandResult failure(String message, UUID id) {
        return new CommandResult(
                message, id.toString(), null, HttpStatus.BAD_REQUEST); // По умолчанию Bad Request
    }

    // failure со строковым идентификатором
    @Deprecated
    public static CommandResult failure(String message, String ident) {
        return new CommandResult(
                message, ident, null, HttpStatus.BAD_REQUEST); // По умолчанию Bad Request
    }

    // failure с несколькими UUID
    @Deprecated
    public static CommandResult failure(String message, List<UUID> failureIds) {
        return new CommandResult(
                message, null, failureIds.stream().map(UUID::toString).toList(), HttpStatus.BAD_REQUEST);
    }
}
