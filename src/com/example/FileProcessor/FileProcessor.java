package com.example.fileprocessor;

import com.example.fileprocessor.parsers.CsvParser;
import com.example.fileprocessor.parsers.XmlParser;

import java.util.*;

public class FileProcessor {
    private final CsvParser csvParser;
    private final XmlParser xmlParser;

    public FileProcessor() {
        this.csvParser = new CsvParser();
        this.xmlParser = new XmlParser();
    }

    public List<Map<String, String>> processFile(String filePath) {
        if (filePath.endsWith(".csv")) {
            return csvParser.loadCsv(filePath);
        } else if (filePath.endsWith(".xml")) {
            return xmlParser.loadXml(filePath);
        } else {
            System.out.println("Формат файла не поддерживается. Пожалуйста, используйте CSV или XML.");
            return null;
        }
    }
}