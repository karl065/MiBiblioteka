package com.mibiblioteka.api.services.usuariosService;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import com.mibiblioteka.api.services.rolesService.ActualizarRolesServices;

@Service
public class CrearUsuariosService {

    private UsuariosRepository usuariosRepository;

    private PasswordEncoder passwordEncoder;

    private ActualizarRolesServices actualizarRolesServices;

    public CrearUsuariosService(UsuariosRepository usuariosRepository,
            PasswordEncoder passwordEncoder,
            ActualizarRolesServices actualizarRolesServices) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
        this.actualizarRolesServices = actualizarRolesServices;
    }

    public Usuarios crearUsuario(Usuarios usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            Usuarios usuarioCreado = usuariosRepository.crear(usuario);

            String usuarioId = usuarioCreado.getId();
            usuarioCreado.setId(usuarioId);

            for (Object rolObj : (Iterable<?>) usuario.getRol()) {
                if (rolObj instanceof Roles rol) {
                    Roles rolActualizado = new Roles();
                    rolActualizado.setUsuarios(List.of(usuarioCreado));
                    actualizarRolesServices.actualizarRol(rol.getId(), rolActualizado);
                }

            }
            return usuarioCreado;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al crear un usuario: " + e.getMessage());
            return null;
        }
    }
}
