package com.mibiblioteka.api.services.estadoLibroService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.repository.estadoLibroRepository.EstadoLibroRepository;

@Service
public class EliminarEstadoLibroServices {

    private EstadoLibroRepository estadoLibroRepository;

    public EliminarEstadoLibroServices(EstadoLibroRepository estadoLibroRepository) {
        this.estadoLibroRepository = estadoLibroRepository;
    }

    public boolean eliminarRol(String id) {

        boolean eliminado = estadoLibroRepository.eliminar(id);

        if (!eliminado) {
            System.out.println("No se encontró el rol con id: " + id);
            return false;
        }

        return true;
    }

}
