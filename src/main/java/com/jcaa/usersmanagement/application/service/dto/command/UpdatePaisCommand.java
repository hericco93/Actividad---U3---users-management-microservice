package com.jcaa.usersmanagement.application.service.dto.command;

public record UpdatePaisCommand(
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
