package by.polikarpov.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T, K> {
    T save(T entity);
    T update(K id, T entity);
    Optional<T> getById(K id);
    List<T> getAll();
    void delete(K id);
}
