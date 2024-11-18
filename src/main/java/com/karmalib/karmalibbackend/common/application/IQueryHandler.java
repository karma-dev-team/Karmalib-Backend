package com.karmalib.karmalibbackend.common.application;

import java.util.Optional;

public interface IQueryHandler<Q extends BaseQuery, R> {
    R handle(Q s);
}
