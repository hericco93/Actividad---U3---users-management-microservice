package com.jcaa.usersmanagement.domain.exception;

public class PaisAlreadyExistsException extends DomainException {

    private PaisAlreadyExistsException(final String message) {
        super(message);
    }

    public static PaisAlreadyExistsException becauseNombreAlreadyExists(final String nombre) {
        return new PaisAlreadyExistsException("Ya existe un país registrado con el nombre: " + nombre);
    }
}