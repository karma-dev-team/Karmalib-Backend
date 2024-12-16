package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.file.application.commands.InputFileCommand;
import lombok.Data;

import java.util.List;

@Data
public class CreatePostCommand extends BaseCommand {
    private String text;
    private String title;
    private List<InputFileCommand> files;
}
