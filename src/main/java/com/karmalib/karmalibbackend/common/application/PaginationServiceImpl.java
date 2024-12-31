package com.karmalib.karmalibbackend.common.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationServiceImpl implements IPaginationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> List<T> getPaginatedData(Class<T> entityType, int page, int size) {
        int offset = (page - 1) * size; // Рассчитываем смещение
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                .setFirstResult(Math.abs(offset)) // Смещение
                .setMaxResults(size)    // Лимит записей
                .getResultList();
    }

    @Override
    public <T> long getTotalCount(Class<T> entityType) {
        return entityManager.createQuery("SELECT COUNT(e) FROM " + entityType.getSimpleName() + " e", Long.class)
                .getSingleResult();
    }
}
