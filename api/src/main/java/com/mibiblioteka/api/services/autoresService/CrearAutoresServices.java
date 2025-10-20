package com.mibiblioteka.api.services.autoresService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.repository.autoresRepository.AutoresRepository;

@Service
public class CrearAutoresServices {

    private AutoresRepository autoresRepository;

    public CrearAutoresServices(AutoresRepository autoresRepository) {
        this.autoresRepository = autoresRepository;
    }

    public Autores crearAutor(Autores autor) {
        return autoresRepository.crear(autor);
    }
}
