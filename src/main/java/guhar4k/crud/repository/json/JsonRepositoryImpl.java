package guhar4k.crud.repository.json;

import guhar4k.crud.model.Storable;
import guhar4k.crud.utils.Library;
import guhar4k.crud.utils.Utils;
import guhar4k.crud.utils.json.JsonUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

public class JsonRepositoryImpl<T extends Storable> {
    private Utils<Storable> ioUtils;

    public JsonRepositoryImpl(String fileName, Type listType) {
        ioUtils = new JsonUtils<T>(new File(Library.RES_DIR, fileName), listType);
        ioUtils.initRepositoryFile();
    }

    public void save(Storable storableObj) {
        List<Storable> storableList = getAllRecords();
        storableObj.setId(storableList.size() + 1);
        ioUtils.saveRecord(storableObj);
    }

    public Storable getById(Long id) {
        List<Storable> storableList = getAllRecords();
        return storableList.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
    }

    public void update(Storable storableObj) {
        List<Storable> storableList = getAllRecords();
        Storable foundStorable = storableList.stream()
                .filter(s -> s.getId() == storableObj.getId())
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record: " + storableObj));
        foundStorable.cloneFrom(storableObj);
        ioUtils.rewriteAllRecords(storableList);
    }

    public void deleteById(Long id) {
        List<Storable> storableList = getAllRecords();
        Storable foundStorable = storableList.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id: " + id));
        storableList.remove(foundStorable);
        ioUtils.rewriteAllRecords(storableList);
    }

    public List<Storable> getAllRecords() {
        return ioUtils.getAll();
    }
}
