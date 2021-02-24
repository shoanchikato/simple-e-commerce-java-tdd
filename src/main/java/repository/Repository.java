package repository;

import java.util.List;

public interface Repository<T> {
    T create(T entity);
    T edit(int id, T entity);
    T getById(int id);
    List<T> list();
}
