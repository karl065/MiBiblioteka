package com.mibiblioteka.api.services.usuariosService;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

@Service
public class ActualizarUsuariosService {

    private UsuariosRepository usuariosRepository;

    private PasswordEncoder passwordEncoder;

    public ActualizarUsuariosService(UsuariosRepository usuariosRepository,
            ObtenerUsuariosService obtenerUsuariosService,
            PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuariosRepository = usuariosRepository;
    }

    public Optional<Usuarios> actualizarUsuario(String id, Usuarios updates) {
        try {
            if (updates.getPassword() != null) {
                String encodedPassword = passwordEncoder.encode(updates.getPassword());
                updates.setPassword(encodedPassword);
                return usuariosRepository.actualizar(id, updates);
            } else {
                return usuariosRepository.actualizar(id, updates);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al actualizar un usuario: " + e.getMessage());
            return null;
        }
    }
}
