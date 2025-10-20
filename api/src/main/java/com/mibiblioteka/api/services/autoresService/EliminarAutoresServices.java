package com.mibiblioteka.api.services.autoresService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.repository.autoresRepository.AutoresRepository;

@Service
public class EliminarAutoresServices {

    private AutoresRepository autoresRepository;

    public EliminarAutoresServices(AutoresRepository autoresRepository) {
        this.autoresRepository = autoresRepository;
    }

    public boolean eliminarAutor(String id) {

        boolean eliminado = autoresRepository.eliminar(id);

        if (!eliminado) {
            System.out.println("No se encontró el rol con id: " + id);
            return false;
        }

        return true;
    }

}
