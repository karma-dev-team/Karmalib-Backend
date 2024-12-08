package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.enums.GroupContactTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_contacts")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupIntegrationEntity extends BaseEntity {
    private String name;
    private String link;
    @Enumerated(EnumType.STRING)
    private GroupContactTypes type;

    @ManyToOne
    private FileEntity file;
}
