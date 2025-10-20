package com.mibiblioteka.api.services.librosService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.repository.librosRepository.LibrosRepository;

@Service
public class EliminarLibrosServices {

    private LibrosRepository librosRepository;

    public EliminarLibrosServices(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public boolean eliminarLibro(String id) {

        boolean eliminado = librosRepository.eliminar(id);

        if (!eliminado) {
            System.out.println("No se encontró el rol con id: " + id);
            return false;
        }

        return true;
    }

}
