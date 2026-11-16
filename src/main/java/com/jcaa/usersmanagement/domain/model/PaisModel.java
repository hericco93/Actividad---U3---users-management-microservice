package com.jcaa.usersmanagement.domain.model;

import lombok.Value;

@Value
public class PaisModel {

    String id;
    String nombre;
    String presidente;
    String continente;
    String nombreHimno;
    long numHabitantes;
    int numDepartamentos;
    int numMunicipios;
    String idiomaPrincipal;
    int numUniversidades;
    String tipoDemocracia;

    public static PaisModel create(
            final String id,
            final String nombre,
            final String presidente,
            final String continente,
            final String nombreHimno,
            final long numHabitantes,
            final int numDepartamentos,
            final int numMunicipios,
            final String idiomaPrincipal,
            final int numUniversidades,
            final String tipoDemocracia) {
        return new PaisModel(
                id,
                nombre,
                presidente,
                continente,
                nombreHimno,
                numHabitantes,
                numDepartamentos,
                numMunicipios,
                idiomaPrincipal,
                numUniversidades,
                tipoDemocracia);
    }
}