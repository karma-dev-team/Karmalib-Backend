package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.BaseCommand;
import com.karmalib.karmalibbackend.user.domain.enums.GroupContactTypes;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddIntegrationCommand extends BaseCommand {
    private UUID groupId;   // ID группы
    private GroupContactTypes type;    // Тип интеграции (например, "VK", "Instagram")
    private String link;     // Ссылка на интеграцию
}