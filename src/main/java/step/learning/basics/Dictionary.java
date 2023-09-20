package step.learning.basics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    private HashMap<String, String> dict;
    private static final String JSON_FILE = "dictionary.json";
    private Scanner kbScanner = new Scanner(System.in);
    private boolean menu = true;

    public Dictionary() {
        dict = new HashMap<>();
        loadDictionaryFromJson();
    }

    public void menuTable() {
        Scanner scanner = new Scanner(System.in);

        while (menu) {
            System.out.println("Меню:");
            System.out.println("1. Добавить слово в словарь");
            System.out.println("2. Перевести слово");
            System.out.println("3. Вывести весь словарь");
            System.out.println("4. Удалить словарь");
            System.out.println("5. Выйти из программы");
            System.out.print("Выберите действие (1/2/3/4/5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToDict();
                    break;
                case 2:
                    translate();
                    break;
                case 3:
                    printDictionary();
                    break;
                case 4:
                    deleteDict();
                    return;
                case 5:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие из меню.");
            }
        }
    }

    private void loadDictionaryFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE));
            Gson gson = new Gson();
            TypeToken<HashMap<String, String>> token = new TypeToken<>() {
            };
            dict = gson.fromJson(reader, token.getType());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("JSON файл не найден. Создан новый словарь.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDictionaryToJson() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE));
            Gson gson = new Gson();
            gson.toJson(dict, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToDict() {
        menu = false;

        while (!menu) {
            System.out.println("Введите слово: ");
            String word = kbScanner.next();
            System.out.println("Введите перевод: ");
            String translation = kbScanner.next();
            dict.put(word, translation);
            saveDictionaryToJson();
            System.out.println("Готово.");
            menu = true;
        }
    }

    public void deleteDict(){
        String filePath = "dictionary.json";
        File fileToDelete = new File(filePath);
        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("Файл успешно удален.");
            } else {
                System.out.println("Не удалось удалить файл.");
            }
        } else {
            System.out.println("Файл не существует.");
        }
        menu = true;

    }

    public void printDictionary() {
        System.out.println("Словарь:");

        if (dict.isEmpty()) {
            System.out.println("Пусто");
        } else {
            for (Map.Entry<String, String> entry : dict.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public String translation(String input) {
        for (Map.Entry<String, String> entry : dict.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(input)) {
                return entry.getValue();
            }
            if (entry.getValue().equalsIgnoreCase(input)) {
                return entry.getKey();
            }
        }
        return "Не найдено в словаре.";
    }

    public void translate() {
        menu = false;

        while (!menu) {
            System.out.println("Введите слово для перевода: ");
            String input = kbScanner.next();
            String resultTranslate = translation(input);
            System.out.println("Результат перевода: " + resultTranslate);
            menu = true;
        }
    }
}
