package com.mibiblioteka.api.services.librosService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.repository.librosRepository.LibrosRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerLibrosServices {

    private LibrosRepository librosRepository;

    public ObtenerLibrosServices(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public List<Libros> obtenerLibros(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, Libros.class);
        return librosRepository.buscar(query);
    }

    public Optional<Libros> obtenerLibrosPorId(String id) {
        return librosRepository.buscarPorId(id);
    }

}
