package com.mibiblioteka.api.controllers.estadoLibroControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mibiblioteka.api.services.estadoLibroService.EliminarEstadoLibroServices;

@RestController
@RequestMapping("/estadoLibro/eliminar")
public class EliminarEstadoLibroController {

    private final EliminarEstadoLibroServices eliminarEstadoLibroServices;

    @Autowired
    public EliminarEstadoLibroController(EliminarEstadoLibroServices eliminarEstadoLibroServices) {
        this.eliminarEstadoLibroServices = eliminarEstadoLibroServices;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstadoLibro(@PathVariable String id) {
        try {
            boolean eliminado = eliminarEstadoLibroServices.eliminarEstadoLibro(id);
            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el estado libro con id: " + id);
            }
            return ResponseEntity.ok("✅ Estado libro eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
