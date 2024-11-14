package com.karmalib.karmalibbackend.user.application.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class IncorrectInvitationException extends Exception {
    private final UUID invitationId;

    public IncorrectInvitationException(UUID invitationId) {
        super();
        this.invitationId = invitationId;
    }
}
