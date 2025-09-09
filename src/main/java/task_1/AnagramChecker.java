package task_1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnagramChecker {

    public static boolean isAnagram(String s, String t) {
        // Нормализуем строки: приводим к нижнему регистру и удаляем пробелы
        s = s.toLowerCase().replaceAll("\\s+", "");
        t = t.toLowerCase().replaceAll("\\s+", "");

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> charCount = new HashMap<>();

        // Подсчет символов первой строки
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Проверка второй строки
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }

        return charCount.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Проверка анаграмм ===");
        System.out.print("Введите первую строку: ");
        String s = scanner.nextLine();

        System.out.print("Введите вторую строку: ");
        String t = scanner.nextLine();

        boolean result = isAnagram(s, t);
        System.out.println("Результат: " + result);

        if (result) {
            System.out.println("Строки являются анаграммами!");
        } else {
            System.out.println("Строки НЕ являются анаграммами.");
        }

        scanner.close();
    }
}