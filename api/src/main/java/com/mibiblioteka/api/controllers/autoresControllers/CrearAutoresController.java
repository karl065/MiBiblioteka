package com.mibiblioteka.api.controllers.autoresControllers;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.services.autoresService.CrearAutoresServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores/crear")
public class CrearAutoresController {

    private final CrearAutoresServices crearAutoresServices;

    @Autowired
    public CrearAutoresController(CrearAutoresServices crearAutoresServices) {
        this.crearAutoresServices = crearAutoresServices;
    }

    @PostMapping
    public ResponseEntity<?> crearAutor(@RequestBody Autores autor) {
        try {
            Autores nuevoAutor = crearAutoresServices.crearAutor(autor);
            return ResponseEntity.ok(nuevoAutor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
