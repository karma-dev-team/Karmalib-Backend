package com.karmalib.karmalibbackend.file.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.domain.enums.FileType;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.InputStream;
import java.util.UUID;

@Data
public class SaveFileCommand extends BaseCommand {
    // save strategies
    @NonNull
    private FileType type;
    @Nullable
    private String name;
    @Nullable
    private UUID fileId;
    @Nullable
    private String link;
    @Nullable
    private InputStream stream;
}
