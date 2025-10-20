package com.mibiblioteka.api.repository.generosRepository;

import org.springframework.stereotype.Repository;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.repository.base.BaseRepository;

@Repository
public class GenerosRepository extends BaseRepository<Generos> {
    public GenerosRepository() {
        super(Generos.class);
    }
}
