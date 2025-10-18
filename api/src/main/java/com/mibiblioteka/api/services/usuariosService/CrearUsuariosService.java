package com.mibiblioteka.api.services.usuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

@Service
public class CrearUsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuarios crearUsuariosServices(Usuarios usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuariosRepository.save(usuario);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al crear un usuario: " + e.getMessage());
            return null;
        }
    }
}
