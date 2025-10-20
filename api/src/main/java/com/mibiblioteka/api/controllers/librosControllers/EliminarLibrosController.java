package com.mibiblioteka.api.controllers.librosControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mibiblioteka.api.services.librosService.EliminarLibrosServices;

@RestController
@RequestMapping("/libros/eliminar")
public class EliminarLibrosController {

    private final EliminarLibrosServices eliminarLibrosServices;

    @Autowired
    public EliminarLibrosController(EliminarLibrosServices eliminarLibrosServices) {
        this.eliminarLibrosServices = eliminarLibrosServices;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable String id) {
        try {
            boolean eliminado = eliminarLibrosServices.eliminarLibro(id);
            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el libro con id: " + id);
            }
            return ResponseEntity.ok("✅ Libro eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
