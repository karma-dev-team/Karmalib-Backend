package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteTitleCommand extends BaseCommand {
    private UUID titleId;
}