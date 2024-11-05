package com.karmalib.karmalibbackend.common.application;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public interface ICommandHandler<T extends ICommand, ID> {
    Optional<ID> handle(T command) throws IOException, TimeoutException;;
}
