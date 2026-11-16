package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetPaisByIdQuery;
import com.jcaa.usersmanagement.domain.model.PaisModel;

public interface GetPaisByIdUseCase {
    PaisModel execute(GetPaisByIdQuery query);
}