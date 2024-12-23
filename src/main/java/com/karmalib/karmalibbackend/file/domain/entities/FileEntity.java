package com.karmalib.karmalibbackend.file.domain.entities;

import com.karmalib.karmalibbackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "files")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity extends BaseEntity  {
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String path;
    @Builder.Default
    private int size = 0;
    @Column(nullable = false)
    private String mimeType;
    private int width;
    private int height;
}
