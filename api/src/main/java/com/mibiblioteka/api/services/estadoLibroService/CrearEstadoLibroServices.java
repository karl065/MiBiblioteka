package com.mibiblioteka.api.services.estadoLibroService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.repository.estadoLibroRepository.EstadoLibroRepository;

@Service
public class CrearEstadoLibroServices {

    private EstadoLibroRepository estadoLibroRepository;

    public CrearEstadoLibroServices(EstadoLibroRepository estadoLibroRepository) {
        this.estadoLibroRepository = estadoLibroRepository;
    }

    public EstadoLibro crearEstadoLibro(EstadoLibro rol) {
        return estadoLibroRepository.crear(rol);
    }
}
