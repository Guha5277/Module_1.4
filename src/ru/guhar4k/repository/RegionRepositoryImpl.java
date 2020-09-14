package ru.guhar4k.repository;

import ru.guhar4k.model.Region;

import java.io.File;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {
    private final String FILE_PATH = "regions.txt";
    private final File repoFile = new File(FILE_PATH);

    @Override
    public void save(Region obj) {

    }

    @Override
    public Region getById(Long aLong) {
        return null;
    }

    @Override
    public void update(Region obj, Long aLong) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Region> getAll() {
        return null;
    }
}
