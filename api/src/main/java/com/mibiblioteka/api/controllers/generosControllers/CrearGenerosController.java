package com.mibiblioteka.api.controllers.generosControllers;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.services.generosService.CrearGenerosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generos/crear")
public class CrearGenerosController {

    private final CrearGenerosServices crearGenerosServices;

    @Autowired
    public CrearGenerosController(CrearGenerosServices crearGenerosServices) {
        this.crearGenerosServices = crearGenerosServices;
    }

    @PostMapping
    public ResponseEntity<?> crearGenero(@RequestBody Generos genero) {
        try {
            Generos nuevoGenero = crearGenerosServices.crearGenero(genero);
            return ResponseEntity.ok(nuevoGenero);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
