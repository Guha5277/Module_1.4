package guhar4k.crud.utils;

import java.io.File;
import java.io.IOException;

public abstract class MainUtils<T> implements Utils<T>{
    final File repositoryFile;

    public MainUtils(File repositoryFile) {
        this.repositoryFile = repositoryFile;
    }

    @Override
    public void initRepositoryFile() {
        try {
            if (!repositoryFile.exists()) repositoryFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
