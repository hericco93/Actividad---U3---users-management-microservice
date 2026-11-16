package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PaisModel;
import java.util.List;

public interface GetAllPaisesPort {
    List<PaisModel> getAll();
}