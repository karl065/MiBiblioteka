package com.mibiblioteka.api.repository.librosRepository;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LibrosRepository extends BaseRepository<Libros> {
    public LibrosRepository() {
        super(Libros.class);
    }
}
