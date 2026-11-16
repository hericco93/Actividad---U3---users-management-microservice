package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PaisModel;

public interface SavePaisPort {
    PaisModel save(PaisModel pais);
}