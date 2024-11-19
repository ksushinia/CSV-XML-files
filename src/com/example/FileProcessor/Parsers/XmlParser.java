package com.example.fileprocessor.parsers;

import java.io.*;
        import java.util.*;
        import javax.xml.parsers.*;
        import org.xml.sax.*;
        import org.xml.sax.helpers.DefaultHandler;

public class XmlParser {
    public List<Map<String, String>> loadXml(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(new File(filePath), new DefaultHandler() {
                Map<String, String> record;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if ("item".equals(qName)) {
                        record = new HashMap<>();
                        record.put("city", attributes.getValue("city"));
                        record.put("street", attributes.getValue("street"));
                        record.put("house", attributes.getValue("house"));
                        record.put("floor", attributes.getValue("floor"));
                        data.add(record);
                    }
                }
            });

            System.out.println("XML файл успешно загружен.");
        } catch (Exception e) {
            System.out.println("Ошибка при чтении XML файла: " + e.getMessage());
        }

        return data;
    }
}