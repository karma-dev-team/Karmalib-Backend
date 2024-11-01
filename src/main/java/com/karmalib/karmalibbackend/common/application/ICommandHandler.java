package com.karmalib.karmalibbackend.common.application;

public interface ICommandHandler<T> {
    public void execute(T command);
}
