package com.karmalib.karmalibbackend.file.infrastructure.files;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Optional;

@Component
@Profile("production") // Используется только для профиля production
public class MinIOFileService implements IFileService {

    private final MinioClient minioClient;

    public MinIOFileService(
            @Value("${minio.endpoint}") String endpoint,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    @Override
    public void uploadFile(String bucketName, String fileName, InputStream content, String contentType) throws Exception {
        if (!bucketExists(bucketName)) {
            createBucket(bucketName);
        }
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(content, -1, 10485760)
                        .contentType(contentType)
                        .build()
        );
    }

    @Override
    public Optional<InputStream> downloadFile(String bucketName, String fileName) throws Exception {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return Optional.of(stream);
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey")) {
                return Optional.empty();
            }
            throw e;
        }
    }

    @Override
    public void deleteFile(String bucketName, String fileName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    @Override
    public boolean fileExists(String bucketName, String fileName) throws Exception {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey")) {
                return false;
            }
            throw e;
        }
    }

    private boolean bucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
        );
    }

    private void createBucket(String bucketName) throws Exception {
        minioClient.makeBucket(
                MakeBucketArgs.builder().bucket(bucketName).build()
        );
    }
}