package com.mibiblioteka.api.repository.rolesRepository;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RolesRepository extends BaseRepository<Roles> {
    public RolesRepository() {
        super(Roles.class);
    }
}
