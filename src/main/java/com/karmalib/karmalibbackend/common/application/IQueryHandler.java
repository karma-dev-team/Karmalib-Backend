package com.karmalib.karmalibbackend.common.application;

public interface IQueryHandler<Q extends BaseQuery, R> {
    R handle(Q s);
}
