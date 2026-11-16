package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreatePaisRestRequest(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El presidente es obligatorio") String presidente,
        @NotBlank(message = "El continente es obligatorio") String continente,
        @NotBlank(message = "El nombre del himno es obligatorio") String nombreHimno,
        @Min(value = 0, message = "El número de habitantes debe ser mayor o igual a 0") long numHabitantes,
        @Min(value = 0, message = "El número de departamentos debe ser mayor o igual a 0") int numDepartamentos,
        @Min(value = 0, message = "El número de municipios debe ser mayor o igual a 0") int numMunicipios,
        @NotBlank(message = "El idioma principal es obligatorio") String idiomaPrincipal,
        @Min(value = 0, message = "El número de universidades debe ser mayor o igual a 0") int numUniversidades,
        @NotBlank(message = "El tipo de democracia es obligatorio") String tipoDemocracia) {}