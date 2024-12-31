package com.karmalib.karmalibbackend.common.application;

import com.karmalib.karmalibbackend.common.domain.AccessDenied;

import java.util.Optional;

public interface IQueryHandler<Q extends BaseQuery, R> {
    R handle(Q s) throws AccessDenied;
}
