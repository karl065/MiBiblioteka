package com.mibiblioteka.api.services.autoresService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.repository.autoresRepository.AutoresRepository;

@Service
public class ActualizarAutoresServices {

    private AutoresRepository autoresRepository;

    public ActualizarAutoresServices(AutoresRepository autoresRepository) {
        this.autoresRepository = autoresRepository;
    }

    public Optional<Autores> actualizarAutor(String id, Autores update) {
        return autoresRepository.actualizar(id, update);
    }
}
