package com.karmalib.karmalibbackend.file.infrastructure.files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Component
@Profile("development") // Используется только для профиля development
public class LocalFileService implements IFileService {

    private final Path basePath;

    public LocalFileService(@Value("${file.base-path}") String basePath) {
        this.basePath = Paths.get(basePath).toAbsolutePath();
        try {
            Files.createDirectories(this.basePath); // Создать базовую директорию
        } catch (IOException e) {
            throw new RuntimeException("Failed to create base directory for file storage", e);
        }
    }

    @Override
    public void uploadFile(String bucketName, String fileName, InputStream content, String contentType) throws Exception {
        Path bucketPath = basePath.resolve(bucketName);
        Files.createDirectories(bucketPath); // Создать директорию бакета

        Path filePath = bucketPath.resolve(fileName);
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            content.transferTo(outputStream); // Скопировать содержимое файла
        }
    }

    @Override
    public void deleteFiles(String bucketName, List<String> fileNames) throws Exception {
        // TODO
    }


    @Override
    public Optional<InputStream> downloadFile(String bucketName, String fileName) throws Exception {
        Path filePath = basePath.resolve(bucketName).resolve(fileName);
        if (!Files.exists(filePath)) {
            return Optional.empty();
        }
        return Optional.of(Files.newInputStream(filePath));
    }

    @Override
    public void deleteFile(String bucketName, String fileName) throws Exception {
        Path filePath = basePath.resolve(bucketName).resolve(fileName);
        Files.deleteIfExists(filePath);
    }

    @Override
    public boolean fileExists(String bucketName, String fileName) throws Exception {
        Path filePath = basePath.resolve(bucketName).resolve(fileName);
        return Files.exists(filePath);
    }
}
