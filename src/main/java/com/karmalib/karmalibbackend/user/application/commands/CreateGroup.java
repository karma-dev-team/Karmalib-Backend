package com.karmalib.karmalibbackend.user.application.commands;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.application.ICommandHandler;
import com.karmalib.karmalibbackend.user.domain.entities.GroupEntity;
import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.GroupRepository;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateGroup implements ICommandHandler<CreateGroupCommand> {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateGroup(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommandResult handle(CreateGroupCommand command) {
        // Validate that no group with the same name exists
        if (groupRepository.existsByName(command.getName())) {
            return CommandResult.failure("Group with this name already exists", command.getName());
        }

        // Fetch users by the provided list of IDs
        List<UserEntity> users = userRepository.findAllById(command.getMembers());
        if (users.size() != command.getMembers().size()) {
            return CommandResult.failure("Some user IDs are invalid", command.getMembers().toString());
        }

        // Create and save the new group
        GroupEntity group = GroupEntity.builder()
                .name(command.getName())
                .description(command.getDescription())
                .shortDescription(command.getShortDescription())
                .users(users) // Associate the fetched users
                .build();

        groupRepository.save(group);

        // Return success with the group ID
        return CommandResult.success(group.id);
    }
}
