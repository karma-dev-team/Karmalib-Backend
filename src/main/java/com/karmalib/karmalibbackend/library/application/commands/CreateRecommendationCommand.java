package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateRecommendationCommand extends BaseCommand {
    private UUID recommendedTitleId;
    private UUID titleFromToRecommendId;
}