package com.karmalib.karmalibbackend.file.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.file.application.exceptions.ApplicationFileNotFoundException;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.file.infrastructure.files.IFileService;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class SaveFile implements ICommandHandler<SaveFileCommand> {
    // used for 
    @Autowired
    private IFileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Value("file.bucket-name")
    private String bucketName;

    @Override
    @Transactional
    public CommandResult handle(SaveFileCommand command) {
        if (command.getFileId() != null) {
            var file = fileRepository.findById(command.getFileId()).orElse(null);
            if (file == null) {
                // throwing bc not always you check when file is uploaded
                throw new ApplicationFileNotFoundException("No file found with given id");
            }
            return CommandResult.success(file.id);
        }
        if (command.getStream() != null) {
            String fileName;

            if (command.getName() == null)
                fileName = UUID.randomUUID().toString();
            else
                fileName = command.getName();

            byte[] stream = new byte[0];
            try {
                int length = command.getStream().read(stream);
            } catch (IOException e) {
                return CommandResult.failure("Failed to read stream");
            }
            try {
                fileService.uploadFile(bucketName, fileName, command.getStream(), command.getType().toString());
            } catch (Exception e) {
                return CommandResult.failure("Error while uploading file, error:" + e.getMessage());
            }
            
            var file = FileEntity.builder()
                    .size(stream.length)
                    .mimeType(command.getType().toString())
                    .build();
            file.setName(buildName(file.id, command.getName()));
            file.setPath(buildPath(file.id));
            fileRepository.save(file);

            return CommandResult.success(file.id);
        }

        return CommandResult.failure("No file id, or stream found");
    }

    private String buildPath(UUID id) {
        return "/static/files/" + id;
    }

    private String buildName(UUID id, String name) {
        return id.toString() + "_" + name;
    }
}
