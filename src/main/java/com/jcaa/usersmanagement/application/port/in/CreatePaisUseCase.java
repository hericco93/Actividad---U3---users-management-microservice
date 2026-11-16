package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreatePaisCommand;
import com.jcaa.usersmanagement.domain.model.PaisModel;

public interface CreatePaisUseCase {
    PaisModel execute(CreatePaisCommand command);
}