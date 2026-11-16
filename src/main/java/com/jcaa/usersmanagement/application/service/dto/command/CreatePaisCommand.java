package com.jcaa.usersmanagement.application.service.dto.command;

public record CreatePaisCommand(
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