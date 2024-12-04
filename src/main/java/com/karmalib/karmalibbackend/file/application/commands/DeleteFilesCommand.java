package com.karmalib.karmalibbackend.file.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;
import lombok.NonNull;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;
import java.util.UUID;

@Data
public class DeleteFilesCommand extends BaseCommand {
    @NonNull
    private List<UUID> fileIds;
    private String reason;
    private String bucket = "files";
    @JsonIgnore
    private String token;
}
