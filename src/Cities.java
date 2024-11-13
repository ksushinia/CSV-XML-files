//Чтение файлов + вывод названий городов (2)
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class Cities {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь до файла-справочника или 'exit' для завершения:");
            String filePath = scanner.nextLine();
            if (filePath.equalsIgnoreCase("exit")) {
                System.out.println("Завершение программы.");
                break;
            }

            // Обработка файла
            List<Map<String, String>> records;
            if (filePath.endsWith(".csv")) {
                records = loadCsv(filePath);
            } else if (filePath.endsWith(".xml")) {
                records = loadXml(filePath);
            } else {
                System.out.println("Формат файла не поддерживается. Пожалуйста, используйте CSV или XML.");
                continue;
            }

            // Вывод списка городов
            printCities(records);
        }
    }

    // Чтение CSV файла
    private static List<Map<String, String>> loadCsv(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Пропустить заголовок
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Map<String, String> record = new HashMap<>();
                    record.put("city", parts[0].replaceAll("\"", "").trim());
                    data.add(record);
                }
            }
            System.out.println("CSV файл успешно загружен.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }
        return data;
    }

    // Чтение XML файла
    private static List<Map<String, String>> loadXml(String filePath) {
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

    // Вывод уникальных городов
    private static void printCities(List<Map<String, String>> records) {
        Set<String> cities = new HashSet<>();
        for (Map<String, String> record : records) {
            String city = record.get("city");
            if (city != null) {
                cities.add(city);
            }
        }

        System.out.println("Список городов:");
        for (String city : cities) {
            System.out.println(city);
        }
    }
}

