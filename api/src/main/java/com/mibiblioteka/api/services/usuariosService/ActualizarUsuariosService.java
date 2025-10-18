package com.mibiblioteka.api.services.usuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

@Service
public class ActualizarUsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuarios actualizarUsuarioService(String id, Usuarios usuario) {
        try {
            
            return usuariosRepository.findById(id).map(u -> {
                u.setNombre(usuario.getNombre());
                if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                    u.setPassword(passwordEncoder.encode(usuario.getPassword()));
                }
                u.setRol(usuario.getRol());
                return usuariosRepository.save(u);
            }).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al actualizar un usuario: " + e.getMessage());
            return null;
        }
    }
}
