package ru.guhar4k.repository;

public interface GenericRepository <T, ID> {
    void save(T obj);
    T getById(ID id);
    void update(T obj, ID id);
    void deleteById(ID id);
}
