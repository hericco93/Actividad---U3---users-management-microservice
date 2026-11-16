package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreatePaisUseCase;
import com.jcaa.usersmanagement.application.port.in.DeletePaisUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllPaisesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetPaisByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdatePaisUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreatePaisRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatePaisRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.PaisRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.PaisRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
@RequiredArgsConstructor
@Tag(name = "Países", description = "CRUDL de gestión de países")
@SecurityRequirement(name = "bearerAuth")
public class PaisRestController {

    private final CreatePaisUseCase createPaisUseCase;
    private final UpdatePaisUseCase updatePaisUseCase;
    private final DeletePaisUseCase deletePaisUseCase;
    private final GetPaisByIdUseCase getPaisByIdUseCase;
    private final GetAllPaisesUseCase getAllPaisesUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo país")
    public PaisRestResponse create(@Valid @RequestBody final CreatePaisRestRequest request) {
        return PaisRestMapper.toResponse(
                createPaisUseCase.execute(PaisRestMapper.toCreateCommand(request)));
    }

    @GetMapping
    @Operation(summary = "Listar todos los países")
    public List<PaisRestResponse> getAll() {
        return PaisRestMapper.toResponseList(getAllPaisesUseCase.execute());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener país por ID")
    public PaisRestResponse getById(@PathVariable final String id) {
        return PaisRestMapper.toResponse(
                getPaisByIdUseCase.execute(PaisRestMapper.toGetByIdQuery(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un país existente")
    public PaisRestResponse update(
            @PathVariable final String id, @Valid @RequestBody final UpdatePaisRestRequest request) {
        return PaisRestMapper.toResponse(
                updatePaisUseCase.execute(PaisRestMapper.toUpdateCommand(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un país")
    public void delete(@PathVariable final String id) {
        deletePaisUseCase.execute(PaisRestMapper.toDeleteCommand(id));
    }
}
