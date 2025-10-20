package com.mibiblioteka.api.repository.estadoLibroRepository;

import org.springframework.stereotype.Repository;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.repository.base.BaseRepository;

@Repository
public class EstadoLibroRepository extends BaseRepository<EstadoLibro> {
    public EstadoLibroRepository() {
        super(EstadoLibro.class);
    }
}
