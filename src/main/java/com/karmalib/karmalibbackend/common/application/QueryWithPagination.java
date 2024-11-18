package com.karmalib.karmalibbackend.common.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class QueryWithPagination extends BaseQuery {
    private int pageNumber; // Номер текущей страницы
    private int pageSize;   // Количество элементов на странице

    public QueryWithPagination() {
        super();
        this.pageNumber = 1; // По умолчанию первая страница
        this.pageSize = 10;  // По умолчанию 10 элементов на страницу
    }

    public QueryWithPagination(String queryId, Instant timestamp, int pageNumber, int pageSize) {
        super(queryId, timestamp);
        this.pageNumber = Math.max(pageNumber, 1); // Минимальная страница — 1
        this.pageSize = Math.max(pageSize, 1);     // Минимальный размер страницы — 1
    }
}
