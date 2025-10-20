package com.mibiblioteka.api.repository.usuariosRepository;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosRepository extends BaseRepository<Usuarios> {

    public UsuariosRepository() {
        super(Usuarios.class);
    }
    
}

