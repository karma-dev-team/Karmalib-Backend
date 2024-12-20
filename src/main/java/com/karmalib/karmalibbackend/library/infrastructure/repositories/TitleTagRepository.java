package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.TitleTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TitleTagRepository extends JpaRepository<TitleTagEntity, UUID> {
    List<TitleTagEntity> findAllByNameIn(List<String> tags);
}
