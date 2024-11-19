package com.example.data;

import java.util.*;

public class Statistics {
    public void printDuplicates(List<Map<String, String>> records) {
        Map<String, Integer> duplicates = new HashMap<>();

        for (Map<String, String> record : records) {
            String key = record.get("city") + "|" + record.get("street") + "|" + record.get("house");
            duplicates.put(key, duplicates.getOrDefault(key, 0) + 1);
        }

        System.out.println("Дублирующиеся записи:");
        for (Map.Entry<String, Integer> entry : duplicates.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Адрес: " + entry.getKey().replace("|", ", ") + " - Повторений: " + entry.getValue());
            }
        }
    }

    public void printFloorStats(List<Map<String, String>> records) {
        Map<String, Map<String, Integer>> cityFloors = new HashMap<>();

        for (Map<String, String> record : records) {
            String city = record.get("city");
            String floor = record.get("floor");

            cityFloors.putIfAbsent(city, new HashMap<>());
            Map<String, Integer> floors = cityFloors.get(city);
            floors.put(floor, floors.getOrDefault(floor, 0) + 1);
        }

        System.out.println("Статистика этажей по городам:");
        for (Map.Entry<String, Map<String, Integer>> cityEntry : cityFloors.entrySet()) {
            System.out.println("Город: " + cityEntry.getKey());
            for (Map.Entry<String, Integer> floorEntry : cityEntry.getValue().entrySet()) {
                System.out.println("  " + floorEntry.getKey() + "-этажных зданий: " + floorEntry.getValue());
            }
        }
    }
}

