package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllPaisesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllPaisesPort;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPaisesService implements GetAllPaisesUseCase {

    private final GetAllPaisesPort getAllPaisesPort;

    @Override
    public List<PaisModel> execute() {
        return getAllPaisesPort.getAll();
    }
}