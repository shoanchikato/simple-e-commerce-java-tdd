package repository;

import model.Entity;

import java.util.List;

public abstract class RepositoryImpl<T extends Entity> implements Repository<T>{
    protected List<T> entities;

    public RepositoryImpl(
            List<T> entities
    ) {
        this.entities = entities;
    }

    public T create(T entity) {
        Long id = entities.size() + 1L;

        entity.setId(id);
        entities.add(entity);

        return entity;
    }

    public T edit(int id, T entity) {
        // guard against out of bound exception
        if(id <= 0 || id > entities.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        T newEntity;
        entities.get(id - 1);
        newEntity = entity;
        newEntity.setId(((long) id));

//        try {
//             entities.get(id.intValue() - 1);
//             newEntity = entity;
//             newEntity.setId(id);
//        } catch (Throwable e) {
//            throw new IllegalArgumentException("id doesn't exist " + id);
//        }

        return newEntity;
    }

    public T getById(int id) {
        // guard against out of bound exception
        if(id <= 0 || id > entities.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        return entities.get(id - 1);
    }

    public List<T> list() {
        return entities;
    }
}
