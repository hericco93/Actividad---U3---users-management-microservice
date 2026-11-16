package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.PaisModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PaisPersistenceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class PaisPersistenceMapper {

    private PaisPersistenceMapper() {}

    public static PaisPersistenceDto fromModelToDto(final PaisModel model) {
        return new PaisPersistenceDto(
                model.getId(),
                model.getNombre(),
                model.getPresidente(),
                model.getContinente(),
                model.getNombreHimno(),
                model.getNumHabitantes(),
                model.getNumDepartamentos(),
                model.getNumMunicipios(),
                model.getIdiomaPrincipal(),
                model.getNumUniversidades(),
                model.getTipoDemocracia());
    }

    public static PaisModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return new PaisModel(
                rs.getString("id"),
                rs.getString("nombre"),
                rs.getString("presidente"),
                rs.getString("continente"),
                rs.getString("nombre_himno"),
                rs.getLong("num_habitantes"),
                rs.getInt("num_departamentos"),
                rs.getInt("num_municipios"),
                rs.getString("idioma_principal"),
                rs.getInt("num_universidades"),
                rs.getString("tipo_democracia"));
    }

    public static List<PaisModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<PaisModel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fromResultSetToModel(rs));
        }
        return list;
    }
}