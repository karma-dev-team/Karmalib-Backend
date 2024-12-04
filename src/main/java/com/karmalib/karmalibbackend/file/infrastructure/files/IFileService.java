package com.karmalib.karmalibbackend.file.infrastructure.files;


import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface IFileService {
    /**
     * Загрузка файла в хранилище.
     *
     * @param bucketName имя бакета
     * @param fileName   имя файла
     * @param content    содержимое файла
     * @param contentType тип контента (например, "image/png")
     * @throws Exception если произошла ошибка
     */
    void uploadFile(String bucketName, String fileName, InputStream content, String contentType) throws Exception;

    /**
     * Получение файла из хранилища.
     *
     * @param bucketName имя бакета
     * @param fileName   имя файла
     * @return поток с содержимым файла
     * @throws Exception если произошла ошибка
     */
    Optional<InputStream> downloadFile(String bucketName, String fileName) throws Exception;

    /**
     * Удаление файла из хранилища.
     *
     * @param bucketName имя бакета
     * @param fileName   имя файла
     * @throws Exception если произошла ошибка
     */
    void deleteFile(String bucketName, String fileName) throws Exception;

    void deleteFiles(String bucketName, List<String> fileNames) throws Exception;

    /**
     * Проверка существования файла в хранилище.
     *
     * @param bucketName имя бакета
     * @param fileName   имя файла
     * @return true, если файл существует
     * @throws Exception если произошла ошибка
     */
    boolean fileExists(String bucketName, String fileName) throws Exception;
}