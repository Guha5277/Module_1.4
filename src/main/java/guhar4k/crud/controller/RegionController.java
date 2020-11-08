package guhar4k.crud.controller;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Region;
import guhar4k.crud.repository.GenericRepository;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.repository.io.JavaIORegionRepositoryImpl;
import guhar4k.crud.repository.json.JsonRepositoryImpl;

import java.util.List;

public class RegionController {
//    private RegionRepository regionRepository;
    private GenericRepository regionRepository;

    public RegionController() {
//        regionRepository = new JavaIORegionRepositoryImpl();
//        regionRepository = new JsonRegionRepositoryImpl();
        regionRepository = new JsonRepositoryImpl<Region, Long>("regions.json", new TypeToken<List<Region>>(){}.getType());
    }

    public void save(Region region) {
        regionRepository.save(region);
    }

    public Region getById(Long id) {
        return (Region) regionRepository.getById(id);
    }

    public void update(Region region) {
        regionRepository.update(region);
    }

    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    public List<Region> getAll() {
        return regionRepository.getAll();
    }
}
