package by.itstep.phonebook.conection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Connection {

    public static List<String> readFullFile(File file) {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            return br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFileOneLine(File file, String logLine) {
        try (BufferedWriter br =
                     new BufferedWriter(new FileWriter(file, true))) {
            br.newLine();
            br.write(logLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long getNumberOFRecords(String path) {
        try {
            String lastLine = Files.lines(Paths.get(path)).reduce((a, b) -> b).orElse(null);
            if (lastLine == null) {
                //TODO throw Exception?
                return -1L;
            } else {
                int index = lastLine.indexOf(",");
                if (index == -1) {
                    return 0L;
                }
                lastLine = lastLine.substring(0, index);
                if ("id".equals(lastLine)) return 0L;
                return Long.valueOf(lastLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
