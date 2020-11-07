package guhar4k.crud.repository.io;

import guhar4k.crud.model.Region;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.utils.IOUtils;
import guhar4k.crud.utils.Library;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static guhar4k.crud.utils.Library.REC_END;
import static guhar4k.crud.utils.Library.REC_PART_DELIMITER;

public class JavaIORegionRepositoryImpl implements RegionRepository {
    private final String FILE_NAME = "regions.txt";
    private final File repositoryFile = new File(Library.RES_DIR, FILE_NAME);

    public JavaIORegionRepositoryImpl() {
        if (!repositoryFile.exists()) IOUtils.createNewFile(repositoryFile);
    }

    @Override
    public void save(Region region) {
        updateRegionId(region);
        String record = region.getId() + REC_PART_DELIMITER + region.getName() + REC_END;
        IOUtils.writeRecord(repositoryFile, record);
    }

    @Override
    public Region getById(Long id) {
        List<Region> regionsList = getAll();
        Optional<Region> region = regionsList.stream().filter(r -> r.getId() == id).findFirst();
        return region.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
    }

    @Override
    public void update(Region region) {
        List<Region> regionsList = getAll();
        Optional<Region> resultRegion = regionsList.stream().filter(r -> r.getId() == region.getId()).findFirst();
        resultRegion.orElseThrow(() -> new NoSuchElementException("Repository do not contains updated item"));
        resultRegion.get().setName(region.getName());
        saveAll(regionsList);
    }

    @Override
    public void deleteById(Long id) {
        List<Region> regionsList = getAll();
        Optional<Region> region = regionsList.stream().filter(r -> r.getId() == id).findFirst();
        region.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
        regionsList.remove(region.get());
        saveAll(regionsList);
    }

    @Override
    public List<Region> getAll() {
        String fileContentString = IOUtils.fileToString(repositoryFile);
        if (fileContentString.length() == 0 || !fileContentString.contains(REC_END)) return new ArrayList<>();

        String[] encodedRegions = fileContentString.split(REC_END);

        return getRegionsFromEncodedStrings(encodedRegions);
    }

    private void saveAll(List<Region> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(r -> sb.append(r.getId()).append(REC_PART_DELIMITER).append(r.getName()).append(REC_END));

        IOUtils.rewriteAllRecords(repositoryFile, sb.toString());
    }

    private List<Region> getRegionsFromEncodedStrings(String[] encodedRegions) {
        int ID_PART = 0;
        int NAME_PART = 1;
        return Arrays.stream(encodedRegions).map(s -> {
            String[] parts = s.split(REC_PART_DELIMITER);
            return new Region(Long.valueOf(parts[ID_PART]), parts[NAME_PART]);
        }).collect(Collectors.toList());
    }

    private void updateRegionId(Region region) {
        List<Region> regionList = getAll();
        long id = regionList.size() == 0 ? 1 : regionList.get(regionList.size() - 1).getId() + 1;
        region.setId(id);
    }
}
