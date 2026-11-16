package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdatePaisUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPaisByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePaisPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdatePaisCommand;
import com.jcaa.usersmanagement.domain.exception.InvalidPaisDataException;
import com.jcaa.usersmanagement.domain.exception.PaisNotFoundException;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePaisService implements UpdatePaisUseCase {

    private final GetPaisByIdPort getPaisByIdPort;
    private final UpdatePaisPort updatePaisPort;

    @Override
    public PaisModel execute(final UpdatePaisCommand command) {
        if (command.nombre() == null || command.nombre().isBlank()) {
            throw InvalidPaisDataException.because("El nombre del país es obligatorio.");
        }
        if (command.numHabitantes() < 0 || command.numDepartamentos() < 0 || command.numMunicipios() < 0 || command.numUniversidades() < 0) {
            throw InvalidPaisDataException.because("Los valores numéricos no pueden ser negativos.");
        }
        final PaisModel existing =
                getPaisByIdPort
                        .getById(command.id())
                        .orElseThrow(() -> PaisNotFoundException.becauseIdWasNotFound(command.id()));

        final PaisModel updated =
                new PaisModel(
                        existing.getId(),
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
        return updatePaisPort.update(updated);
    }
}