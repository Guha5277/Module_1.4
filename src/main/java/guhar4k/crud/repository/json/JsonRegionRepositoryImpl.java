package guhar4k.crud.repository.json;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Region;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.utils.JsonUtils;
import guhar4k.crud.utils.Library;
import guhar4k.crud.utils.Utils;

import java.io.File;
import java.util.List;

public class JsonRegionRepositoryImpl implements RegionRepository {
    private final String FILE_NAME = "posts.json";
    private Utils<Region> ioUtils;

    public JsonRegionRepositoryImpl() {
        ioUtils = new JsonUtils<>(new File(Library.RES_DIR, FILE_NAME), new TypeToken<List<Region>>(){}.getType());
        ioUtils.initRepositoryFile();
    }

    @Override
    public void save(Region region) {
        List<Region> regionList = getAll();
        //region.setId(regionList.size() == 0 ? 1 : regionList.size() + 1);
        ioUtils.saveRecord(region);
    }

    @Override
    public Region getById(Long id) {
        return null;
    }

    @Override
    public void update(Region region) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Region> getAll() {
        return ioUtils.getAll();
    }
}
