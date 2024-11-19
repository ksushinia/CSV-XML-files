package com.example.fileprocessor.parsers;

import java.io.*;
import java.util.*;

public class CsvParser {
    public List<Map<String, String>> loadCsv(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Map<String, String> record = new HashMap<>();
                    record.put("city", parts[0].replaceAll("\"", "").trim());
                    record.put("street", parts[1].replaceAll("\"", "").trim());
                    record.put("house", parts[2].trim());
                    record.put("floor", parts[3].trim());
                    data.add(record);
                }
            }
            System.out.println("CSV файл успешно загружен.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }

        return data;
    }
}