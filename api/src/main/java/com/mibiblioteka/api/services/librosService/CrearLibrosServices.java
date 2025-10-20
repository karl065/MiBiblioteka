package com.mibiblioteka.api.services.librosService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.repository.librosRepository.LibrosRepository;

@Service
public class CrearLibrosServices {

    private LibrosRepository librosRepository;

    public CrearLibrosServices(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public Libros crearLibro(Libros rol) {
        return librosRepository.crear(rol);
    }
}
