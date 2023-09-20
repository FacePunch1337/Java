package step.learning.basics;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    private HashMap<String, String> dict;
    Scanner kbScanner = new Scanner(System.in);
    public Dictionary() {
        dict = new HashMap<>();
        initializeDictionary();

    }

    private void initializeDictionary() {
        dict.put("apple", "яблоко");
        dict.put("banana", "банан");
        dict.put("cat", "кот");
        dict.put("dog", "собака");
    }


    public String translate(String input) {
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



    public void TranslateDict() {

        while (true) {

            System.out.println("Введите слово для перевода: ");
            String input = kbScanner.next();
            String translation = translate(input);
            System.out.println("Результат перевода: " + translation);
        }
    }


}