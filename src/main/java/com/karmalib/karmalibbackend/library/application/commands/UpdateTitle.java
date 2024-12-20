package com.karmalib.karmalibbackend.library.application.commands;


import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.common.infrastrcuture.AccessPolicy;
import com.karmalib.karmalibbackend.file.application.commands.SaveFile;
import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleRepository;
import com.karmalib.karmalibbackend.library.infrastructure.repositories.TitleTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateTitle implements ICommandHandler<UpdateTitleCommand> {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private AccessPolicy accessPolicy;

    @Autowired
    private SaveFile saveFile;

    @Autowired
    private TitleTagRepository titleTagRepository;

    @Override
    public CommandResult handle(UpdateTitleCommand command) {
        // 1. Проверка существования тайтла
        var title = titleRepository.findById(command.getTitleId()).orElse(null);
        if (title == null) {
            return CommandResult.notFound("Тайтл не найден", command.getTitleId());
        }

        // 2. Проверка прав доступа
        if (!accessPolicy.isUserSelf(title.getOwnedBy().getId()) && !accessPolicy.isAdmin()) {
            return CommandResult.forbidden("Вы не владелец тайтла или администратор");
        }

        // 3. Обновление простых полей
        if (command.getName() != null)
            title.setName(command.getName());

        if (command.getDescription() != null)
            title.setDescription(command.getDescription());

        if (command.getAlternateNames() != null)
            title.setAlternateNames(command.getAlternateNames()); // Разбиваем строку на список

        if (command.getPgRating() != null)
            title.setPgRating(command.getPgRating());

        // 4. Обновление тэгов
        if (command.getTags() != null && !command.getTags().isEmpty()) {
            List<TitleTagEntity> tags = titleTagRepository.findAllByNameIn(command.getTags());
            title.setTags(tags);
        }

        // 5. Обновление обложки
        if (command.getCoverImage() != null) {
            var file = saveFile.handleInner(command.getCoverImage());
            title.setCoverImage(file);
        }

        // 6. Сохранение изменений
        titleRepository.save(title);

        return CommandResult.success(title.getId());
    }
}
