package com.karmalib.karmalibbackend.common.application;

import java.util.List;

public interface IPaginationService {
    <T> List<T> getPaginatedData(Class<T> entityType, int page, int size);
    <T> long getTotalCount(Class<T> entityType);
}