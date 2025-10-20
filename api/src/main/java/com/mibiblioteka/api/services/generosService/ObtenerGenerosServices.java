package com.mibiblioteka.api.services.generosService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.repository.generosRepository.GenerosRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerGenerosServices {

    private GenerosRepository generosRepository;

    public ObtenerGenerosServices(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    public List<Generos> obtenerGeneros(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, Generos.class);
        return generosRepository.buscar(query);
    }

    public Optional<Generos> obtenerGeneroPorId(String id) {
        return generosRepository.buscarPorId(id);
    }

}
