package com.mibiblioteka.api.controllers.librosControllers;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.services.librosService.ObtenerLibrosServices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros/obtener")
public class ObtenerLibrosController {

    private final ObtenerLibrosServices librosService;

    public ObtenerLibrosController(ObtenerLibrosServices librosService) {
        this.librosService = librosService;
    }

    @GetMapping
    public List<Libros> obtenerLibros(@RequestParam Map<String, Object> filtros) {
        // Si filtros está vacío, traerá todos los libros
        return librosService.obtenerLibros(filtros);
    }

    @GetMapping("/{id}")
    public Libros obtenerLibroPorId(@PathVariable String id) throws Exception {
        Map<String, Object> filtros = Map.of("id", id);
        List<Libros> resultado = librosService.obtenerLibros(filtros);
        if (resultado.isEmpty()) {
            throw new Exception("Rol no encontrado con id: " + id);
        }
        return resultado.get(0);
    }

    @PostMapping("/filtrar")
    public List<Libros> filtrarLibros(@RequestBody Map<String, Object> filtros) {
        return librosService.obtenerLibros(filtros);
    }
}
