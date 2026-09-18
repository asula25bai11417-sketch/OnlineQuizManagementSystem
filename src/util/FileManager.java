package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<String> readFromFile(String fileName) {
        List<String> data = new ArrayList<>();

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
                return data;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return data;
    }

    public static void appendToFile(String fileName, String data) {
        try {
            File file = new File(fileName);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            FileWriter writer = new FileWriter(file, true);
            writer.write(data + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeToFile(String fileName, List<String> data) {
        try {
            File file = new File(fileName);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file));

            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
} 