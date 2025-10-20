package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.CrearUsuariosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios/crear")
public class CrearUsuariosController {

    private final CrearUsuariosService crearUsuariosService;

    @Autowired
    public CrearUsuariosController(CrearUsuariosService crearUsuariosService) {
        this.crearUsuariosService = crearUsuariosService;
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuarios usuario) {
        try {

            if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                throw new IllegalArgumentException("El usuario debe tener al menos un rol asignado.");
            }

            Usuarios nuevoUsuario = crearUsuariosService.crearUsuario(usuario);
            System.out.println("Creando usuario: " + nuevoUsuario);

            return ResponseEntity.ok(nuevoUsuario);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo crear el usuario", e);
        }

    }

}
