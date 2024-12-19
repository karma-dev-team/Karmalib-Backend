package com.karmalib.karmalibbackend.file.application.services;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.file.application.commands.DeleteFiles;
import com.karmalib.karmalibbackend.file.application.commands.DeleteFilesCommand;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import com.karmalib.karmalibbackend.file.application.queries.GetFile;
import com.karmalib.karmalibbackend.file.application.queries.GetFilesList;
import com.karmalib.karmalibbackend.file.application.queries.GetFilesListQuery;
import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.file.application.queries.GetFileQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private DeleteFiles deleteFilesHandler;

    @Autowired
    private SaveFile saveFileHandler;

    @Autowired
    private GetFile getFileHandler;

    @Autowired
    private GetFilesList getFilesListHandler;

    public CommandResult deleteFiles(DeleteFilesCommand command) {
        return deleteFilesHandler.handle(command);
    }

    public CommandResult saveFile(InputFileCommand command) {
        return saveFileHandler.handle(command);
    }

    // TODO: implement batch file upload
    public List<CommandResult> saveFiles(List<InputFileCommand> commands) {
        List<CommandResult> results = new ArrayList<>();
        for (InputFileCommand command : commands) {
            results.add(saveFileHandler.handle(command));
        }

        return results;
    }

    public List<FileModel> getFiles(GetFilesListQuery query) {
        return getFilesListHandler.handle(query);
    }

    public FileModel getFile(GetFileQuery query) {
        return getFileHandler.handle(query);
    }
}
