package com.mibiblioteka.api.services.generosService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.repository.generosRepository.GenerosRepository;

@Service
public class ActualizarGenerosServices {

    private GenerosRepository generosRepository;

    public ActualizarGenerosServices(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    public Optional<Generos> actualizarGenero(String id, Generos update) {
        return generosRepository.actualizar(id, update);
    }
}
