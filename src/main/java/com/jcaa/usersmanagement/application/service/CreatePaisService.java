package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreatePaisUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPaisByNombrePort;
import com.jcaa.usersmanagement.application.port.out.SavePaisPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreatePaisCommand;
import com.jcaa.usersmanagement.domain.exception.InvalidPaisDataException;
import com.jcaa.usersmanagement.domain.exception.PaisAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePaisService implements CreatePaisUseCase {

    private final SavePaisPort savePaisPort;
    private final GetPaisByNombrePort getPaisByNombrePort;

    @Override
    public PaisModel execute(final CreatePaisCommand command) {
        validate(command);
        if (getPaisByNombrePort.getByNombre(command.nombre().trim()).isPresent()) {
            throw PaisAlreadyExistsException.becauseNombreAlreadyExists(command.nombre());
        }
        final PaisModel pais =
                PaisModel.create(
                        UUID.randomUUID().toString(),
                        command.nombre().trim(),
                        command.presidente().trim(),
                        command.continente().trim(),
                        command.nombreHimno().trim(),
                        command.numHabitantes(),
                        command.numDepartamentos(),
                        command.numMunicipios(),
                        command.idiomaPrincipal().trim(),
                        command.numUniversidades(),
                        command.tipoDemocracia().trim());
        return savePaisPort.save(pais);
    }

    private void validate(final CreatePaisCommand c) {
        if (c.nombre() == null || c.nombre().isBlank()) {
            throw InvalidPaisDataException.because("El nombre del país es obligatorio.");
        }
        if (c.numHabitantes() < 0 || c.numDepartamentos() < 0 || c.numMunicipios() < 0 || c.numUniversidades() < 0) {
            throw InvalidPaisDataException.because("Los valores numéricos no pueden ser negativos.");
        }
    }
}