package guhar4k.crud.repository.json;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Region;
import guhar4k.crud.model.Storable;
import guhar4k.crud.repository.RegionRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRegionRepositoryImpl extends JsonRepositoryImpl<Region> implements RegionRepository {

    public JsonRegionRepositoryImpl(String fileName, Type listType) {
        super(fileName, listType);
    }

    @Override
    public void save(Region region) {
        super.save(region);
    }

    @Override
    public Region getById(Long id) {
        return (Region) super.getById(id);
    }

    @Override
    public void update(Region region) {
        super.update(region);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Region> getAll() {
        List<Storable> storableList = super.getAllRecords();
        if (storableList.size() == 0) return new ArrayList<>();
        return storableList.stream()
                .map(r -> (Region) r)
                .collect(Collectors.toList());
    }
}
