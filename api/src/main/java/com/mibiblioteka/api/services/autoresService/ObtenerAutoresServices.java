package com.mibiblioteka.api.services.autoresService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.repository.autoresRepository.AutoresRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerAutoresServices {

    private AutoresRepository autoresRepository;

    public ObtenerAutoresServices(AutoresRepository autoresRepository) {
        this.autoresRepository = autoresRepository;
    }

    public List<Autores> obtenerAutores(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, Autores.class);
        return autoresRepository.buscar(query);
    }

    public Optional<Autores> obtenerAutorPorId(String id) {
        return autoresRepository.buscarPorId(id);
    }

}
