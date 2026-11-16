package com.jcaa.usersmanagement.domain.exception;

public class InvalidPaisDataException extends DomainException {

    private InvalidPaisDataException(final String message) {
        super(message);
    }

    public static InvalidPaisDataException because(final String reason) {
        return new InvalidPaisDataException(reason);
    }
}