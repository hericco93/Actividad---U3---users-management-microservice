package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PaisModel;
import java.util.Optional;

public interface GetPaisByIdPort {
    Optional<PaisModel> getById(String id);
}