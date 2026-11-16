package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record PaisPersistenceDto(
        String id,
        String nombre,
        String presidente,
        String continente,
        String nombreHimno,
        long numHabitantes,
        int numDepartamentos,
        int numMunicipios,
        String idiomaPrincipal,
        int numUniversidades,
        String tipoDemocracia) {}