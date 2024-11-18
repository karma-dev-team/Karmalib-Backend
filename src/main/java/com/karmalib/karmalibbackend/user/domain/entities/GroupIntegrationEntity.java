package com.karmalib.karmalibbackend.user.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import com.karmalib.karmalibbackend.user.domain.enums.GroupContactTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private GroupContactTypes type;

    @ManyToOne
    private FileEntity file;
}
