package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreatePaisCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeletePaisCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdatePaisCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetPaisByIdQuery;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreatePaisRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatePaisRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.PaisRestResponse;

import java.util.List;

public final class PaisRestMapper {

    private PaisRestMapper() {}

    public static CreatePaisCommand toCreateCommand(final CreatePaisRestRequest r) {
        return new CreatePaisCommand(
                r.nombre(),
                r.presidente(),
                r.continente(),
                r.nombreHimno(),
                r.numHabitantes(),
                r.numDepartamentos(),
                r.numMunicipios(),
                r.idiomaPrincipal(),
                r.numUniversidades(),
                r.tipoDemocracia());
    }

    public static UpdatePaisCommand toUpdateCommand(final String id, final UpdatePaisRestRequest r) {
        return new UpdatePaisCommand(
                id,
                r.nombre(),
                r.presidente(),
                r.continente(),
                r.nombreHimno(),
                r.numHabitantes(),
                r.numDepartamentos(),
                r.numMunicipios(),
                r.idiomaPrincipal(),
                r.numUniversidades(),
                r.tipoDemocracia());
    }

    public static DeletePaisCommand toDeleteCommand(final String id) {
        return new DeletePaisCommand(id);
    }

    public static GetPaisByIdQuery toGetByIdQuery(final String id) {
        return new GetPaisByIdQuery(id);
    }

    public static PaisRestResponse toResponse(final PaisModel m) {
        return new PaisRestResponse(
                m.getId(),
                m.getNombre(),
                m.getPresidente(),
                m.getContinente(),
                m.getNombreHimno(),
                m.getNumHabitantes(),
                m.getNumDepartamentos(),
                m.getNumMunicipios(),
                m.getIdiomaPrincipal(),
                m.getNumUniversidades(),
                m.getTipoDemocracia());
    }

    public static List<PaisRestResponse> toResponseList(final List<PaisModel> list) {
        return list.stream().map(PaisRestMapper::toResponse).toList();
    }
}
