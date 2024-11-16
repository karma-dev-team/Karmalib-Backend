package com.karmalib.karmalibbackend.common.application;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ICommandHandler<T extends BaseCommand> {
    CommandResult handle(T command) throws IOException, TimeoutException;;
}
