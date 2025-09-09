package task_1;
import java.util.HashSet;
import java.util.Set;

public class PowerfulSet {

    /**
     * Возвращает пересечение двух наборов (общие элементы)
     * @param set1 первый набор
     * @param set2 второй набор
     * @return набор содержащий элементы, присутствующие в обоих наборах
     */
    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * Возвращает объединение двух наборов (все уникальные элементы из обоих наборов)
     * @param set1 первый набор
     * @param set2 второй набор
     * @return набор содержащий все элементы из обоих наборов без дубликатов
     */
    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    /**
     * Возвращает относительное дополнение (элементы первого набора без общих со вторым)
     * @param set1 первый набор (из которого вычитаем)
     * @param set2 второй набор (который вычитаем)
     * @return элементы set1, которых нет в set2
     */
    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    // Возвращает симметричную разность (элементы, которых нет в обоих списках, уникальные для каждого)
    public <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> union = union(set1, set2);
        Set<T> intersection = intersection(set1, set2);
        union.removeAll(intersection);
        return union;
    }
}