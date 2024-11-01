package com.karmalib.karmalibbackend.common.domain;

public class AccessDenied extends Exception
{
    public AccessDenied(String message) {
        super("Access denied: " + message);
    }
}