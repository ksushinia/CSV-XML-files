package com.example.application;//package com.example.application;

import com.example.fileprocessor.FileProcessor;
import com.example.data.Statistics;

import java.util.*;

public class DirectoryProcessorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileProcessor fileProcessor = new FileProcessor();

        while (true) {
            System.out.println("Введите путь до файла-справочника или 'exit' для завершения:");
            String filePath = scanner.nextLine();

            if (filePath.equalsIgnoreCase("exit")) {
                System.out.println("Завершение программы.");
                break;
            }

            long startTime = System.currentTimeMillis();
            List<Map<String, String>> records = fileProcessor.processFile(filePath);
            long endTime = System.currentTimeMillis();

            if (records != null && !records.isEmpty()) {
                Statistics statistics = new Statistics();
                statistics.printDuplicates(records);
                statistics.printFloorStats(records);
                System.out.println("Время обработки файла: " + (endTime - startTime) + " мс.");
            }
        }
    }
}