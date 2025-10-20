package com.mibiblioteka.api.repository.autoresRepository;

import org.springframework.stereotype.Repository;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.repository.base.BaseRepository;

@Repository
public class AutoresRepository extends BaseRepository<Autores> {
    public AutoresRepository() {
        super(Autores.class);
    }

}
