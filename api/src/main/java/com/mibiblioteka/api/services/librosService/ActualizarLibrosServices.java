package com.mibiblioteka.api.services.librosService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.repository.librosRepository.LibrosRepository;

@Service
public class ActualizarLibrosServices {

    private LibrosRepository librosRepository;

    public ActualizarLibrosServices(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public Optional<Libros> actualizarLibro(String id, Libros update) {
        return librosRepository.actualizar(id, update);
    }
}
