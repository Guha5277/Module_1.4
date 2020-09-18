package guhar4k.crud.utils;

import java.io.*;

public class IOUtils {
    public static void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRecord(File file, String record) {
        writeToFile(file, record, true);
    }

    public static void rewriteAllRecords(File file, String record) {
        writeToFile(file, record, false);
    }

    private static void writeToFile(File file, String record, boolean append) {
        try (Writer writer = new FileWriter(file, append)) {
            writer.write(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileToString(File file) {
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

}
