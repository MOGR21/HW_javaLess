import java.util.Arrays;
import java.util.Scanner;

public class SortLetters {
    public static void main(String[] args) {
        System.out.println("Введите строку из слов");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("введена строка: "+input);

        // Разделяем строку на слова по пробелу
        String[] words = input.split(" ");

        // Обрабатываем каждое слово
        for (int i = 0; i < words.length; i++) {
            // Переводим слово в нижний регистр
            String lowerWord = words[i].toLowerCase();

            // Преобразуем слово в массив символов
            char[] letters = lowerWord.toCharArray();

            // Сортируем символы по возрастанию
            Arrays.sort(letters);

            // Собираем отсортированные символы обратно в строку
            words[i] = new String(letters);
        }

        // Выводим результат
        System.out.println("Результат по алфавиту: ");
        System.out.println(String.join(" ", words));
    }
}