package com.mibiblioteka.api.services.generosService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.repository.generosRepository.GenerosRepository;

@Service
public class EliminarGenerosServices {

    private GenerosRepository generosRepository;

    public EliminarGenerosServices(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    public boolean eliminarGenero(String id) {

        boolean eliminado = generosRepository.eliminar(id);

        if (!eliminado) {
            System.out.println("No se encontró el rol con id: " + id);
            return false;
        }

        return true;
    }

}
