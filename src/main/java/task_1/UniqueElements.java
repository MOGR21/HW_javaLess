package task_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniqueElements {

    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        // Проверка на пустой список
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        // Создаем Set для хранения уникальных элементов
        Set<T> uniqueSet = new HashSet<>();

        // Добавляем все элементы - дубликаты автоматически игнорируются
        uniqueSet.addAll(list);

        return uniqueSet;
    }

    public static void main(String[] args) {
        // Тестирование с разными типами данных
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1); intList.add(2); intList.add(1); intList.add(3);

        ArrayList<String> strList = new ArrayList<>();
        strList.add("A"); strList.add("B"); strList.add("A"); strList.add("C");

        System.out.println("Уникальные числа: " + getUniqueElements(intList));
        System.out.println("Уникальные строки: " + getUniqueElements(strList));

        // Тест с пустым списком
        System.out.println("Пустой список: " + getUniqueElements(new ArrayList<>()));
    }
}