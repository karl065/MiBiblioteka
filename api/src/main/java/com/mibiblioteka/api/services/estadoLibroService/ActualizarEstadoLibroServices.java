package com.mibiblioteka.api.services.estadoLibroService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.repository.estadoLibroRepository.EstadoLibroRepository;

@Service
public class ActualizarEstadoLibroServices {

    private EstadoLibroRepository estadoLibroRepository;

    public ActualizarEstadoLibroServices(EstadoLibroRepository estadoLibroRepository) {
        this.estadoLibroRepository = estadoLibroRepository;
    }

    public Optional<EstadoLibro> actualizarEstadoLibro(String id, EstadoLibro update) {
        return estadoLibroRepository.actualizar(id, update);
    }
}
