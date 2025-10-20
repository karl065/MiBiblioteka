package com.mibiblioteka.api.services.generosService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.repository.generosRepository.GenerosRepository;

@Service
public class CrearGenerosServices {

    private GenerosRepository generosRepository;

    public CrearGenerosServices(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    public Generos crearGenero(Generos genero) {
        return generosRepository.crear(genero);
    }
}
