package com.mibiblioteka.api.services.usuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

@Service
public class EliminarUsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;
    
    public void eliminarUsuarioServices(String id) {
        usuariosRepository.deleteById(id);
    }

}
