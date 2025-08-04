package org.epoui.attestation01;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вводим покупателей и их средства
        System.out.println("Введите покупателей и их средства в формате: Имя = Сумма; Имя2 = Сумма2; и т.д.: ");
        String[] peopleInput = scanner.nextLine().split(";\\s*");
        Map<String, Person> peopleMap = new HashMap<>();

        for (int i = 0; i < peopleInput.length; i++) {
            String[] parts = peopleInput[i].split("\\s*=\\s*");
            try {
                String name = parts[0].trim();
                double money = Double.parseDouble(parts[1].trim());
                peopleMap.put(name, new Person(name, money));
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + peopleInput[i]);
                i--; // Повторяем ввод для этого покупателя
            }
        }

        // Ввод продуктов
        System.out.println("Введите продукты в формате: Название = Цена; Название2 = Цена2 и т.д.: ");
        String[] productsInput = scanner.nextLine().split(";\\s*");
        Map<String, Product> productsMap = new HashMap<>();

        for (int i = 0; i < productsInput.length; i++) {
            String[] parts = productsInput[i].split("\\s*=\\s*");
            try {
                String name = parts[0].trim();
                double cost = Double.parseDouble(parts[1].trim());
                productsMap.put(name, new Product(name, cost));
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + productsInput[i]);
                i--; // Повторяем ввод для этого продукта
            }
        }

        // Процесс покупки
        System.out.println("Введите информацию о покупке в формате: Имя_Покупателя - Наименование_Покупки (для завершения введите 'END'): ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("END")) {
                break;
            }

            try {
                String[] parts = input.split("\\s*-\\s*");
                String personName = parts[0].trim();
                String productName = parts[1].trim();

                Person person = peopleMap.get(personName);
                Product product = productsMap.get(productName);

                if (person == null) {
                    System.out.println("Покупатель не найден: " + personName);
                    continue;
                }
                if (product == null) {
                    System.out.println("Продукт не найден: " + productName);
                    continue;
                }

                if (person.buyProduct(product)) {
                    System.out.println(personName + " купил " + productName);
                } else {
                    System.out.println(personName + " не может позволить себе " + productName);
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода. Используйте: Имя - Продукт");
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты покупок:");
        for (Person person : peopleMap.values()) {
            System.out.println(person);
        }
        scanner.close();
    }
}