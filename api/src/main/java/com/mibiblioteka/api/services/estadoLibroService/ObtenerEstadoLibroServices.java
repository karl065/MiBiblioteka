package com.mibiblioteka.api.services.estadoLibroService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.repository.estadoLibroRepository.EstadoLibroRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerEstadoLibroServices {

    private EstadoLibroRepository estadoLibroRepository;

    public ObtenerEstadoLibroServices(EstadoLibroRepository estadoLibroRepository) {
        this.estadoLibroRepository = estadoLibroRepository;
    }

    public List<EstadoLibro> obtenerEstadoLibro(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, EstadoLibro.class);
        return estadoLibroRepository.buscar(query);
    }

    public Optional<EstadoLibro> obtenerEstadoLibroPorId(String id) {
        return estadoLibroRepository.buscarPorId(id);
    }

}
