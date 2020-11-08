package guhar4k.crud.repository.json;

import guhar4k.crud.model.Storable;
import guhar4k.crud.repository.GenericRepository;
import guhar4k.crud.utils.Library;
import guhar4k.crud.utils.json.JsonUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

public class JsonRepositoryImpl<T extends Storable, ID> implements GenericRepository<Storable, Long> {
    private JsonUtils<T> ioUtils;

    public JsonRepositoryImpl(String fileName, Type listType) {
        ioUtils = new JsonUtils<T>(new File(Library.RES_DIR, fileName), listType);
    }

    @Override
    public void save(Storable storableObj) {
        List<Storable> storableList = getAll();
        storableObj.setId(storableList.size() + 1);
        ioUtils.saveRecord(storableObj);
    }

    @Override
    public Storable getById(Long id) {
        List<Storable> storableList = getAll();
        return storableList.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
    }

    @Override
    public void update(Storable storableObj) {
        List<Storable> storableList = getAll();
        Storable foundStorable = storableList.stream()
                .filter(s -> s.getId() == storableObj.getId())
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record: " + storableObj));
        int index = storableList.indexOf(foundStorable);
        storableList.set(index, storableObj);
        ioUtils.rewriteAllRecords(storableList);
    }

    @Override
    public void deleteById(Long id) {
        List<Storable> storableList = getAll();
        Storable foundStorable = storableList.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id: " + id));
        storableList.remove(foundStorable);
        ioUtils.rewriteAllRecords(storableList);
    }

    @Override
    public List<Storable> getAll() {
        return ioUtils.getAll();
    }
}
