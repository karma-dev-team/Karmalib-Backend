package com.karmalib.karmalibbackend.file.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.file.infrastructure.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetFilesList implements IQueryHandler<GetFilesListQuery, List<FileModel>> {
    @Autowired
    private FileRepository fileRepository;

    public List<FileModel> handle(GetFilesListQuery query) {
        return fileRepository.findAllById(query.getFileIds())
                .stream()
                .map(FileModel::fromEntity)
                .collect(Collectors.toList());
    }
}
