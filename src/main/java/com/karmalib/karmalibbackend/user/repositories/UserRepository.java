package com.karmalib.karmalibbackend.user.repositories;

import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Cacheable(value = "users")
    Optional<UserEntity> findByEmail(String email);
}
