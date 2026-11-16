package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.PaisModel;
import java.util.List;

public interface GetAllPaisesUseCase {
    List<PaisModel> execute();
}