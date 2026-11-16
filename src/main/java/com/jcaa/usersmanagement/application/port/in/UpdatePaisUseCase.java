package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdatePaisCommand;
import com.jcaa.usersmanagement.domain.model.PaisModel;

public interface UpdatePaisUseCase {
    PaisModel execute(UpdatePaisCommand command);
}