package ua.nure.knysh.carservice.services;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    Optional<T> get(Long id);
    List<T> getAll();
    Optional<Long> create(T t);
    boolean update(T t);
    boolean delete(Long id);
}
