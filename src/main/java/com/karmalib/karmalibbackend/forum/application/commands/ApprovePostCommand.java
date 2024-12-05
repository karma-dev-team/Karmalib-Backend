package com.karmalib.karmalibbackend.forum.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.Data;

import java.util.UUID;

@Data
public class ApprovePostCommand extends BaseCommand {
    private UUID postId;
}
