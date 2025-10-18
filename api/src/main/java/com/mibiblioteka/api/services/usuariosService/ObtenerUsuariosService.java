package com.mibiblioteka.api.services.usuariosService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerUsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired 
    private MongoTemplate mongoTemplate;

    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    public List<Usuarios> obtenerFiltros(Query query) {
        return mongoTemplate.find(query, Usuarios.class);
    }
    
    public Optional<Usuarios> obtenerPorId(String id) {
        return usuariosRepository.findById(id);
    }

    public Usuarios obtenerUsuariosServices(Map<String, Object> filtros, String id) {
        try {
             if (id != null && !id.isBlank()) {
                return usuariosRepository.findById(id).orElse(null);
            }

            if (filtros != null && !filtros.isEmpty()) {
                Query query = FiltroHelper.construirQuery(filtros);
                List<Usuarios> resultados = obtenerFiltros(query);
                return resultados.isEmpty() ? null : resultados.get(0);
            } 

            return null;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return null;
        }
    }


}
