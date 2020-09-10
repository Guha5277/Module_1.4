package ru.guhar4k.repository;

public interface GenericRepository <T, ID> {
    void create(T obj, ID id);
    T get(ID id);
    void update(T obj, ID id);
    void delete(ID id);
}
