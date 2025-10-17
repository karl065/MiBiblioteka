package com.mibiblioteka.api.repository.rolesRepository;

import com.mibiblioteka.api.models.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends MongoRepository<Roles, String> {
    
    Optional<Roles> findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);

}
