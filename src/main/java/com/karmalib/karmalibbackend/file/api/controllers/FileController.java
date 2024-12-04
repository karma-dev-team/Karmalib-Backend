package com.karmalib.karmalibbackend.file.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.CustomResponseEntity;
import com.karmalib.karmalibbackend.file.application.commands.DeleteFilesCommand;
import com.karmalib.karmalibbackend.file.application.commands.SaveFileCommand;
import com.karmalib.karmalibbackend.file.application.queries.*;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.file.application.queries.models.GetFileQuery;
import com.karmalib.karmalibbackend.file.application.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    private ResponseEntity<CustomResponseEntity> buildResponse(CommandResult result) {
        CustomResponseEntity response = CustomResponseEntity.of(
                result.getIsSuccess(),
                UUID.fromString(result.getId()),
                result.getMessage()
        );
        return ResponseEntity.status(result.getIsSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // -------- Команды --------

    // Удаление файлов
    @DeleteMapping
    public ResponseEntity<CustomResponseEntity> deleteFiles(@RequestBody DeleteFilesCommand command) {
        CommandResult result = fileService.deleteFiles(command);
        return buildResponse(result);
    }

    // Сохранение файла
    @PostMapping
    public ResponseEntity<CustomResponseEntity> saveFile(@RequestBody SaveFileCommand command) {
        CommandResult result = fileService.saveFile(command);
        return buildResponse(result);
    }

    // -------- Запросы --------

    // Получение списка файлов
    @GetMapping
    public ResponseEntity<List<FileModel>> getFiles(GetFilesListQuery query) {
        List<FileModel> files = fileService.getFiles(query);
        return ResponseEntity.ok(files);
    }

    // Получение информации о конкретном файле
    @GetMapping("/{fileId}")
    public ResponseEntity<FileModel> getFile(@PathVariable UUID fileId) {
        GetFileQuery query = new GetFileQuery();
        query.setFileId(fileId);
        FileModel file = fileService.getFile(query);
        return ResponseEntity.ok(file);
    }
}
