package guhar4k.crud.utils;

import java.util.List;

public interface Utils<T> {
    void initRepositoryFile();
    void saveRecord(T record);
    void rewriteAllRecords(List<T> records);
    List<T> getAll();
}
