package guhar4k.crud.utils.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import guhar4k.crud.utils.MainUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils<T> extends MainUtils<T> {
    private Type listType;

    public JsonUtils(File repositoryFile, Type listType) {
        super(repositoryFile);
        this.listType = listType;
    }

    @Override
    public void saveRecord(T record) {
        List<T> records = getAll();
        records.add(record);

        try (Writer writer = new FileWriter(getRepositoryFile(), false)) {
            new Gson().toJson(records, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rewriteAllRecords(List<T> records) {
        try (Writer writer = new FileWriter(getRepositoryFile(), false)) {
            new Gson().toJson(records, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(getRepositoryFile()))) {
            List<T> resultList = gson.fromJson(reader, listType);
            if (resultList != null) return resultList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }
}
