package com.karmalib.karmalibbackend.file.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetFile implements IQueryHandler<GetFileQuery, FileModel> {
    @Autowired
    private FileRepository fileRepository;

    public FileModel handle(GetFileQuery query) {
        return fileRepository.findById(query.getFileId())
                .map(FileModel::fromEntity)
                .orElse(null);
    }
}
