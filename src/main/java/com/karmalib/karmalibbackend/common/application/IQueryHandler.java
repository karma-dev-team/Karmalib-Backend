package com.karmalib.karmalibbackend.common.application;

public interface IQueryHandler<Q extends Query, R> {
    public R handle(Q s);
}
