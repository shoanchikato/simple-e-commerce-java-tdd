package service;

import java.util.List;

public interface Service<T> {
    T create(T entity);
    T edit(int id, T entity);
    T getById(int id);
    List<T> list();
}