package com.karmalib.karmalibbackend.common.application;

public interface IQueryHandler<S, R> {
    public R handle(S s);
}
