package com.karmalib.karmalibbackend.library.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTag implements ICommandHandler<CreateTagCommand> {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Override
    public CommandResult handle(CreateTagCommand command) {
        if (!accessPolicy.isAdmin()) {
            return CommandResult.forbidden("Не админ");
        }
        var title = titleRepository.findById(command.getTitleId()).orElse(null);

        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }

        title.getTags().add(
                TitleTagEntity.builder()
                        .name(command.getTag())
                        .build()
        );
        titleRepository.save(title);

        return CommandResult.success(title.id);
    }
}
