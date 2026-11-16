package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeletePaisUseCase;
import com.jcaa.usersmanagement.application.port.out.DeletePaisPort;
import com.jcaa.usersmanagement.application.port.out.GetPaisByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeletePaisCommand;
import com.jcaa.usersmanagement.domain.exception.PaisNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePaisService implements DeletePaisUseCase {

    private final GetPaisByIdPort getPaisByIdPort;
    private final DeletePaisPort deletePaisPort;

    @Override
    public void execute(final DeletePaisCommand command) {
        if (getPaisByIdPort.getById(command.id()).isEmpty()) {
            throw PaisNotFoundException.becauseIdWasNotFound(command.id());
        }
        deletePaisPort.delete(command.id());
    }
}