package com.karmalib.karmalibbackend.library.infrastructure.repositories;

import com.karmalib.karmalibbackend.library.domain.entities.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, UUID> {
}
