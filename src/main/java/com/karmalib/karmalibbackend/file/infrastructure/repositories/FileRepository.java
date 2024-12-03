package com.karmalib.karmalibbackend.file.infrastructure.repositories;

import com.karmalib.karmalibbackend.file.domain.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
