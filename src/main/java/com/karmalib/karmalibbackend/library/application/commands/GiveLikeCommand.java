package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.forum.domain.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GiveLikeCommand extends BaseCommand {
    private UUID titleId;
    private ReactionType reactionType; // Enum: LIKE or DISLIKE
}