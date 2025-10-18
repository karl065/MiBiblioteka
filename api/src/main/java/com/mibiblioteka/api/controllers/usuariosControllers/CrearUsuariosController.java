package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import com.mibiblioteka.api.services.usuariosService.CrearUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios/crear")
public class CrearUsuariosController {

    @Autowired
    private CrearUsuariosService crearUsuariosService;

    @Autowired
    private RolesServices rolesServices; // Servicio de roles

    @PostMapping
    public Usuarios crear(@RequestBody Usuarios usuario) {
        try {
        
            if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                throw new IllegalArgumentException("El usuario debe tener al menos un rol asignado.");
        
            }

        
            Usuarios nuevoUsuario = crearUsuariosService.crearUsuariosServices(usuario);

            usuario.getRol().forEach(rol -> extracted(nuevoUsuario, rol));
            return nuevoUsuario;
        } catch (Exception e) { 
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo asociar el usuario a los roles", e); 
        }

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

