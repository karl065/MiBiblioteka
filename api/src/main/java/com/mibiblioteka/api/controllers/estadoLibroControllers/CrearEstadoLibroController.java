package com.mibiblioteka.api.controllers.estadoLibroControllers;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.services.estadoLibroService.CrearEstadoLibroServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadoLibro/crear")
public class CrearEstadoLibroController {

    private final CrearEstadoLibroServices crearEstadoLibroServices;

    @Autowired
    public CrearEstadoLibroController(CrearEstadoLibroServices crearEstadoLibroServices) {
        this.crearEstadoLibroServices = crearEstadoLibroServices;
    }

    @PostMapping
    public ResponseEntity<?> crearEstadoLibro(@RequestBody EstadoLibro estadoLibro) {
        try {
            EstadoLibro nuevoEstadoLibro = crearEstadoLibroServices.crearEstadoLibro(estadoLibro);
            return ResponseEntity.ok(nuevoEstadoLibro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
