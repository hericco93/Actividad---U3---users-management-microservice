package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetPaisByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPaisByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetPaisByIdQuery;
import com.jcaa.usersmanagement.domain.exception.PaisNotFoundException;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPaisByIdService implements GetPaisByIdUseCase {

    private final GetPaisByIdPort getPaisByIdPort;

    @Override
    public PaisModel execute(final GetPaisByIdQuery query) {
        return getPaisByIdPort
                .getById(query.id())
                .orElseThrow(() -> PaisNotFoundException.becauseIdWasNotFound(query.id()));
    }
}