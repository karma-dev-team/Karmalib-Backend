package com.karmalib.karmalibbackend.user.application.scheduled;

import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GroupDeletionScheduler {

    @Autowired
    private GroupRepository groupRepository;

    // Запускать каждый день в 2:00 ночи
    @Scheduled(cron = "0 0 2 * * *")
    public void deleteMarkedGroups() {
        // Находим все группы, которые отмечены для удаления
        List<GroupEntity> groupsToDelete = groupRepository.findByPendingDeletionTrue();

        groupRepository.deleteAll(groupsToDelete);

        System.out.println("Marked groups deleted: " + groupsToDelete.size());
    }
}
