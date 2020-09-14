package ru.guhar4k.controller;

import ru.guhar4k.model.Region;
import ru.guhar4k.repository.RegionRepository;
import ru.guhar4k.repository.RegionRepositoryImpl;

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
