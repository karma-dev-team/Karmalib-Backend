package com.karmalib.karmalibbackend.file.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.infrastructure.files.IFileService;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeleteFiles implements ICommandHandler<DeleteFilesCommand> {
    @Autowired
    private IFileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Value("file.bucket-name")
    private String bucketName;

    @Autowired
    private AccessPolicy accessPolicy;

    @Transactional
    public CommandResult handle(DeleteFilesCommand command) {
        if (accessPolicy.isAdmin() || accessPolicy.isSystemRequest(command.getToken())) {
            try {
                fileRepository.deleteAllByIdInBatch(command.getFileIds());

                fileService.deleteFiles(bucketName, command.getFileIds().stream().map(UUID::toString).collect(Collectors.toList()));
            } catch (Exception e) {
                return CommandResult.failure(e.getMessage());
            }

            return CommandResult.success(command.getFileIds());
        }
        return CommandResult.failure("You are not authorized to delete files");
    }
}
