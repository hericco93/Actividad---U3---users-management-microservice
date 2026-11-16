package com.jcaa.usersmanagement.domain.exception;

public class PaisNotFoundException extends DomainException {

    private PaisNotFoundException(final String message) {
        super(message);
    }

    public static PaisNotFoundException becauseIdWasNotFound(final String id) {
        return new PaisNotFoundException("No se encontró el país con id: " + id);
    }
}