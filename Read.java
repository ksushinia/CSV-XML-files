//Чтение файлов (1)
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class Read {

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
            if (filePath.endsWith(".csv")) {
                loadCsv(filePath);
            } else if (filePath.endsWith(".xml")) {
                loadXml(filePath);
            } else {
                System.out.println("Формат файла не поддерживается. Пожалуйста, используйте CSV или XML.");
            }
        }
    }

    // Чтение CSV файла
    private static void loadCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Пропустить заголовок
            String line;
            while ((line = br.readLine()) != null) {
                // Чтение строк CSV файла, но не сохраняем данные
            }
            System.out.println("Данные из CSV файла успешно считаны.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }
    }

    // Чтение XML файла
    private static void loadXml(String filePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(filePath), new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    // Чтение элементов XML файла, но не сохраняем данные
                }
            });
            System.out.println("Данные из XML файла успешно считаны.");
        } catch (Exception e) {
            System.out.println("Ошибка при чтении XML файла: " + e.getMessage());
        }
    }
}