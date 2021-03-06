package guhar4k.crud.repository;

import java.util.List;

public interface GenericRepository <T, ID> {
    void save(T obj);
    T getById(ID id);
    void update(T obj);
    void deleteById(ID id);
    List<T> getAll();
}
