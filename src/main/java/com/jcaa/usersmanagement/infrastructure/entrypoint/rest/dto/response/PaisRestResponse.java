package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

public record PaisRestResponse(
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