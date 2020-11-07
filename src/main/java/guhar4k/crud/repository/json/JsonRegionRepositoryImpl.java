package guhar4k.crud.repository.json;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Region;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.utils.json.JsonUtils;
import guhar4k.crud.utils.Library;
import guhar4k.crud.utils.Utils;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class JsonRegionRepositoryImpl implements RegionRepository {
    private final String FILE_NAME = "regions.json";
    private Utils<Region> ioUtils;

    public JsonRegionRepositoryImpl() {
        ioUtils = new JsonUtils<>(new File(Library.RES_DIR, FILE_NAME), new TypeToken<List<Region>>(){}.getType());
        ioUtils.initRepositoryFile();
    }

    @Override
    public void save(Region region) {
        List<Region> regionList = getAll();
        region.setId(regionList.size() == 0 ? 1 : regionList.size() + 1);
        ioUtils.saveRecord(region);
    }

    @Override
    public Region getById(Long id) {
        List<Region> regionList = getAll();
        Optional<Region> region = regionList.stream().filter(r -> r.getId() == id).findFirst();
        return region.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
    }

    @Override
    public void update(Region region) {
        List<Region> regionList = getAll();
        Optional<Region> foundRegion = regionList.stream().filter(r -> r.getId() == region.getId()).findFirst();
        foundRegion.orElseThrow(() -> new NoSuchElementException("Repository do not contains record: " + region)).setName(region.getName());
        ioUtils.rewriteAllRecords(regionList);
    }

    @Override
    public void deleteById(Long id) {
        List<Region> regionList = getAll();
        Optional<Region> foundRegion = regionList.stream().filter(r -> r.getId() == id).findFirst();
        regionList.remove(foundRegion.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id)));
        ioUtils.rewriteAllRecords(regionList);
    }

    @Override
    public List<Region> getAll() {
        return ioUtils.getAll();
    }
}
