package com.mibiblioteka.api.repository.usuariosRepository;

import com.mibiblioteka.api.models.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuariosRepository extends MongoRepository<Usuarios, String> {

    Optional<Usuarios> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
