package com.karmalib.karmalibbackend.common.infrastrcuture;

import com.karmalib.karmalibbackend.user.domain.entities.UserEntity;
import com.karmalib.karmalibbackend.user.domain.enums.UserRole;
import com.karmalib.karmalibbackend.user.infrastructure.repositories.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;

import java.util.UUID;

@Component
public class AccessPolicy {

    private final UserRepository userRepository;

    public AccessPolicy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserFromDatabase(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // Fetch the current logged-in user as an entity and cache it
    @Cacheable(value = "userCache", key = "'currentUser'")
    public UserEntity getCurrentUser() {
        String username = getCurrentUsername();
        return getUserFromDatabase(username);
    }

    // Retrieve the current username from the security context
    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    // Check if the current user is a SuperAdmin; if so, all methods should return true
    public boolean isSuperAdmin() {
        return getCurrentUser().getRoles().contains(UserRole.SuperAdmin);
    }

    // Check if the current user is accessing their own data
    public boolean isUserSelf(UUID userId) {
        if (isSuperAdmin()) return true;
        return getCurrentUser().id.equals(userId);
    }

    // Check if the current user has a specific role
    public boolean hasRole(UserRole userRole) {
        if (isSuperAdmin()) return true;
        return getCurrentUser().getRoles().contains(userRole);
    }

    // Check if a given user entity has a specific role
    public boolean hasRole(UserEntity userEntity, UserRole userRole) {
        if (isSuperAdmin()) return true;
        return userEntity.getRoles().contains(userRole);
    }

    // Check if the current user is a Staff member
    public boolean isStaff() {
        return hasRole(UserRole.Staff);
    }

    public boolean isModerator() {
        return hasRole(UserRole.Moderator);
    }

    public boolean isAdmin() {
        return hasRole(UserRole.Admin);
    }

    // Enforce methods with exception throwing
    public void enforceIsUserSelf(UUID userId) {
        if (!isUserSelf(userId)) {
            throw new AccessDeniedException("Access denied: User is not self.");
        }
    }

    public void enforceHasRole(UserRole userRole) {
        if (!hasRole(userRole)) {
            throw new AccessDeniedException("Access denied: Missing required role " + userRole);
        }
    }

    public void enforceHasRole(UserEntity userEntity, UserRole userRole) {
        if (!hasRole(userEntity, userRole)) {
            throw new AccessDeniedException("Access denied: User does not have the role " + userRole);
        }
    }

    public void enforceIsStaff() {
        if (!isStaff()) {
            throw new AccessDeniedException("Access denied: User is not staff.");
        }
    }

    public void enforceIsModerator() {
        if (!isModerator()) {
            throw new AccessDeniedException("Access denied: User is not a moderator.");
        }
    }

    public void enforceIsAdmin() {
        if (!isAdmin()) {
            throw new AccessDeniedException("Access denied: User is not an admin.");
        }
    }

    public void enforceIsSuperAdmin() {
        if (!isSuperAdmin()) {
            throw new AccessDeniedException("Access denied: User is not super admin.");
        }
    }
}