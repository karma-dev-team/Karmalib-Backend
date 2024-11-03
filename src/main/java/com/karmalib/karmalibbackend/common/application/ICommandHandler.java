package com.karmalib.karmalibbackend.common.application;

import java.util.Optional;

public interface ICommandHandler<T, ID> {
    public Optional<ID> execute(T command);
}
