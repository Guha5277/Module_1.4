package main.java.guhar4k.crud.controller;

import main.java.guhar4k.crud.repository.RegionRepository;
import main.java.guhar4k.crud.repository.RegionRepositoryImpl;
import main.java.guhar4k.crud.model.Region;

import java.util.List;

public class RegionController {
    private RegionRepository regionRepository;

    public RegionController() {
        regionRepository = new RegionRepositoryImpl();
    }

    public void save(Region region) {
        regionRepository.save(region);
    }

    public Region getById(Long id) {
        return regionRepository.getById(id);
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
