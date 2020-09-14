package ru.guhar4k.repository;

import ru.guhar4k.model.Region;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.guhar4k.repository.Library.REC_END;
import static ru.guhar4k.repository.Library.REC_PART_DELIMITER;

public class RegionRepositoryImpl implements RegionRepository {
    private final String RES_DIR = "res/";
    private final String FILE_NAME = "regions.txt";
    private final File repositoryFile = new File(RES_DIR, FILE_NAME);

    public RegionRepositoryImpl() {
        if (!repositoryFile.exists()) createRepoFile();
    }

    @Override
    public void save(Region region) {
        updateRegionId(region);
        String record = region.getId() + REC_PART_DELIMITER + region.getName() + REC_END;
        try (Writer writer = new FileWriter(repositoryFile, true)) {
            writer.write(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Region getById(Long id) {
        List<Region> regionsList = getAll();
        Optional<Region> region = regionsList.stream().filter(r -> r.getId() == id).findFirst();
        //TODO возврат null, генерация исключения?
        return region.orElse(null);
    }

    @Override
    public void update(Region region) {
        List<Region> regionsList = getAll();
        Optional<Region> resultRegion = regionsList.stream().filter(r -> r.getId() == region.getId()).findFirst();
        if (!resultRegion.isPresent()) {
            //TODO делаем что-то, если такой записи в файле нет (уведомляем, игнорируем или бросаем экспешн - что??)
            return;
        }
        resultRegion.get().setName(region.getName());
        saveAll(regionsList);
    }

    @Override
    public void deleteById(Long id) {
        List<Region> regionsList = getAll();
        Optional<Region> region = regionsList.stream().filter(r -> r.getId() == id).findFirst();
        if (!region.isPresent()) {
            //TODO делаем что-то, если такой записи в файле нет (уведомляем, игнорируем или бросаем экспешн - что??)
            return;
        }

        regionsList.remove(region.get());
        saveAll(regionsList);
    }

    @Override
    public List<Region> getAll() {
        String fileContentString = fileToString(repositoryFile);
        if (fileContentString.length() == 0 || !fileContentString.contains(REC_END)) {
            //TODO возвращение null или генерация эксепшена?
            return null;
        }

        String[] encodedRegions = fileContentString.split(REC_END);

        return getRegionsFromEncodedStrings(encodedRegions);
    }

    private void saveAll(List<Region> list){
        StringBuilder sb = new StringBuilder();
        list.forEach(r -> sb.append(r.getId()).append(REC_PART_DELIMITER).append(r.getName()).append(REC_END));

        try (Writer writer = new FileWriter(repositoryFile, false)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRepoFile() {
        try {
            repositoryFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fileToString(File file) {
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private List<Region> getRegionsFromEncodedStrings(String[] encodedStrings){
        int ID_PART = 0;
        int NAME_PART = 1;
        return Arrays.stream(encodedStrings).map(s -> {
            String[] parts = s.split(REC_PART_DELIMITER);
            return new Region(Long.valueOf(parts[ID_PART]), parts[NAME_PART]);
        }).collect(Collectors.toList());
    }

    private void updateRegionId(Region region){
        List<Region> regionList = getAll();
        //TODO проверка на null
        long id = regionList == null ? 1 : regionList.get(regionList.size() - 1).getId() + 1;
        region.setId(id);
    }
}
