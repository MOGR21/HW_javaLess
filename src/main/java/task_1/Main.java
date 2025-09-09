package task_1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PowerfulSet powerfulSet = new PowerfulSet();

        // Создаем тестовые наборы
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(0, 1, 2, 4));

        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        // Тестируем методы
        Set<Integer> intersection = powerfulSet.intersection(set1, set2);
        Set<Integer> union = powerfulSet.union(set1, set2);
        Set<Integer> relativeComplement = powerfulSet.relativeComplement(set1, set2);
        Set<Integer> symmetricDifference = powerfulSet.symmetricDifference(set1, set2);

        System.out.println("Пересечение: " + intersection); // {1, 2}
        System.out.println("Объединение: " + union); // {0, 1, 2, 3, 4}
        System.out.println("Относительное дополнение: " + relativeComplement); // {3}
        System.out.println("Симметричная разность: " + symmetricDifference); // {0, 3, 4}

        // Тестирование со строками
        Set<String> stringSet1 = new HashSet<>(Arrays.asList("круг", "квадрат", "треугольник"));
        Set<String> stringSet2 = new HashSet<>(Arrays.asList("квадрат", "прямоугольник", "ромб"));

        System.out.println("\nСтроковые наборы:");
        System.out.println("set1: " + stringSet1);
        System.out.println("set2: " + stringSet2);
        System.out.println("Пересечение: " + powerfulSet.intersection(stringSet1, stringSet2)); // {квадрат}
        System.out.println("Объединение: " + powerfulSet.union(stringSet1, stringSet2)); // {квадрат, треугольник, круг, ромб, прямоугольник}
        System.out.println("Относительное дополнение: " + powerfulSet.relativeComplement(stringSet1, stringSet2)); // {треугольник, круг}
        System.out.println("Симметричная разность: " + powerfulSet.symmetricDifference(stringSet1, stringSet2)); // {треугольник, круг, ромб, прямоугольник}
    }
}