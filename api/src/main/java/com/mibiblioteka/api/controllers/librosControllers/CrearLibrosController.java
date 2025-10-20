package com.mibiblioteka.api.controllers.librosControllers;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.services.librosService.CrearLibrosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros/crear")
public class CrearLibrosController {

    private final CrearLibrosServices crearLibrosServices;

    @Autowired
    public CrearLibrosController(CrearLibrosServices crearLibrosServices) {
        this.crearLibrosServices = crearLibrosServices;
    }

    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody Libros libro) {
        try {
            Libros nuevoLibro = crearLibrosServices.crearLibro(libro);
            return ResponseEntity.ok(nuevoLibro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
