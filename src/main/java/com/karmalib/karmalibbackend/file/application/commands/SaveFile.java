package com.karmalib.karmalibbackend.file.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.file.application.exceptions.ApplicationFileNotFoundException;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.file.infrastructure.files.IFileService;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class SaveFile implements ICommandHandler<InputFileCommand> {
    @Autowired
    private IFileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Value("file.bucket-name")
    private String bucketName;

    @Override
    @Transactional
    public CommandResult handle(InputFileCommand command) {
        try {
            FileEntity file = handleInner(command);
            return CommandResult.success(file.id);
        } catch (ApplicationFileNotFoundException ex) {
            assert command.getFileId() != null;
            return CommandResult.notFound(ex.getMessage(), command.getFileId());
        } catch (IllegalArgumentException ex) {
            return CommandResult.badRequest(ex.getMessage());
        } catch (Exception ex) {
            return CommandResult.internalServerError("Error while processing file: " + ex.getMessage());
        }
    }

    public FileEntity handleInner(InputFileCommand command) {
        if (command.getFileId() != null) {
            return fileRepository.findById(command.getFileId())
                    .orElseThrow(() -> new ApplicationFileNotFoundException("No file found with given id"));
        }

        if (command.getStream() != null) {
            String fileName = command.getName() == null
                    ? UUID.randomUUID().toString()
                    : command.getName();

            byte[] stream = readStream(command);

            try {
                fileService.uploadFile(bucketName, fileName, command.getStream(), command.getType().toString());
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while uploading file: " + e.getMessage(), e);
            }

            FileEntity file = FileEntity.builder()
                    .size(stream.length)
                    .mimeType(command.getType().toString())
                    .build();

            file.setName(buildName(file.id, command.getName()));
            file.setPath(buildPath(file.id));

            return fileRepository.save(file);
        }

        throw new IllegalArgumentException("No file ID or stream found in the command.");
    }

    private byte[] readStream(InputFileCommand command) {
        try {
            assert command.getStream() != null;
            return command.getStream().readAllBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read input stream", e);
        }
    }

    private String buildPath(UUID id) {
        return "/static/files/" + id;
    }

    private String buildName(UUID id, String name) {
        return id.toString() + "_" + name;
    }
}
