package com.mibiblioteka.api.repository.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class BaseRepository<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 🧠 Buscar documentos según un filtro dinámico.
     * Si el Query está vacío, devuelve todos los registros de la colección.
     */
    public List<T> buscar(Query query) {
        return mongoTemplate.find(query, entityClass);
    }

    /**
     * 🔍 Buscar por ID.
     */
    public Optional<T> buscarPorId(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, entityClass));
    }

    /**
     * 💾 Crear un nuevo documento.
     */
    public T crear(T entidad) {
        return mongoTemplate.save(entidad);
    }

    /**
     * 🧩 Actualizar un documento por ID.
     * Solo actualiza los campos que no sean nulos en la entidad pasada.
     */
    public Optional<T> actualizar(String id, T entity) {
        Update update = buildUpdateFromEntity(entity);
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.updateFirst(query, update, entityClass);
        return buscarPorId(id);
    }

    private Update buildUpdateFromEntity(T entity) {
        Update update = new Update();
        try {
            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    if (value instanceof Collection<?> col) {
                        update.addToSet(field.getName()).each(col.toArray());
                    } else {
                        update.set(field.getName(), value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error construyendo Update desde entidad", e);
        }
        return update;
    }

    /**
     * 🗑️ Eliminar un documento por ID.
     */
    public boolean eliminar(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, entityClass).getDeletedCount() > 0;
    }

    /**
     * ⚙️ Devuelve todos los registros (equivalente a findAll).
     */
    public List<T> obtenerTodos() {
        return mongoTemplate.findAll(entityClass);
    }
}
