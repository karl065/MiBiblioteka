package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import com.mibiblioteka.api.services.usuariosService.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios/crear")
public class CrearUsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private RolesServices rolesServices; // Servicio de roles

    @PostMapping
    public Usuarios crear(@RequestBody Usuarios usuario) {
        // Validar que al menos un rol esté asignado
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener al menos un rol asignado.");
        }

        // Crear usuario
        Usuarios nuevoUsuario = usuariosService.crearUsuario(usuario);

        // Actualizar cada rol para agregar este usuario
        try { 
            usuario.getRol().forEach(rol -> extracted(nuevoUsuario, rol));
        } catch (Exception e) { 
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo asociar el usuario a los roles", e); // { changed code }
        }

        return nuevoUsuario;
    }

    private void extracted(Usuarios nuevoUsuario, Roles rol) {
        try {
            rolesServices.agregarUsuarioAlRol(rol.getNombre(), nuevoUsuario.getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

