package com.mibiblioteka.api.controllers.estadoLibroControllers;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.services.estadoLibroService.ObtenerEstadoLibroServices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadoLibro/obtener")
public class ObtenerEstadoLibroController {

    private final ObtenerEstadoLibroServices estadoLibroService;

    public ObtenerEstadoLibroController(ObtenerEstadoLibroServices estadoLibroService) {
        this.estadoLibroService = estadoLibroService;
    }

    @GetMapping
    public List<EstadoLibro> obtenerEstadoLibro(@RequestParam Map<String, Object> filtros) {
        // Si filtros está vacío, traerá todos los estadoLibro
        return estadoLibroService.obtenerEstadoLibro(filtros);
    }

    @GetMapping("/{id}")
    public EstadoLibro obtenerEstadoLibroPorId(@PathVariable String id) throws Exception {
        Map<String, Object> filtros = Map.of("id", id);
        List<EstadoLibro> resultado = estadoLibroService.obtenerEstadoLibro(filtros);
        if (resultado.isEmpty()) {
            throw new Exception("Estado libro no encontrado con id: " + id);
        }
        return resultado.get(0);
    }

    @PostMapping("/filtrar")
    public List<EstadoLibro> filtrarEstadoLibro(@RequestBody Map<String, Object> filtros) {
        return estadoLibroService.obtenerEstadoLibro(filtros);
    }
}
