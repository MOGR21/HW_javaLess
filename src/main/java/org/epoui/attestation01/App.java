package org.epoui.attestation01;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод покупателей и их средств
        System.out.println("Введите покупателей и их средства в формате: Имя = Сумма; Имя2 = Сумма2; и т.д.: ");
        String[] peopleInput = scanner.nextLine().split(";\\s*");
        Map<String, Person> peopleMap = new HashMap<>();

        for (int i = 0; i < peopleInput.length; i++) {
            String input = peopleInput[i].trim();
            if (input.isEmpty()) continue;  // Пропускаем пустые строки

            try {
                String[] parts = input.split("\\s*=\\s*");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода. Используйте: Имя = Сумма");
                }

                String name = parts[0].trim();
                double money = Double.parseDouble(parts[1].trim());

                if (money < 0) {
                    throw new IllegalArgumentException("Деньги не могут быть отрицательными");
                }

                peopleMap.put(name, new Person(name, money));
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + input + " - " + e.getMessage());
                System.out.println("Пожалуйста, введите корректные данные для этого покупателя:");

                // Запрашиваем повторный ввод для этого покупателя
                boolean validInput = false;
                while (!validInput) {
                    try {
                        String newInput = scanner.nextLine().trim();
                        String[] newParts = newInput.split("\\s*=\\s*");

                        if (newParts.length != 2) {
                            throw new IllegalArgumentException("Неверный формат ввода. Используйте: Имя = Сумма");
                        }

                        String name = newParts[0].trim();
                        double money = Double.parseDouble(newParts[1].trim());

                        if (money < 0) {
                            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
                        }

                        peopleMap.put(name, new Person(name, money));
                        validInput = true;
                    } catch (Exception ex) {
                        System.out.println("Ошибка ввода: " + ex.getMessage());
                        System.out.println("Пожалуйста, попробуйте еще раз:");
                    }
                }
            }
        }

        // Ввод продуктов
        System.out.println("Введите продукты в формате: Название = Цена; Название2 = Цена2 и т.д.: ");
        String[] productsInput = scanner.nextLine().split(";\\s*");
        Map<String, Product> productsMap = new HashMap<>();

        for (int i = 0; i < productsInput.length; i++) {
            String input = productsInput[i].trim();
            if (input.isEmpty()) continue;  // Пропускаем пустые строки

            try {
                String[] parts = input.split("\\s*=\\s*");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода. Используйте: Название = Цена");
                }

                String name = parts[0].trim();
                double cost = Double.parseDouble(parts[1].trim());

                if (cost < 0) {
                    throw new IllegalArgumentException("Цена не может быть отрицательной");
                }

                productsMap.put(name, new Product(name, cost));
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + input + " - " + e.getMessage());
                System.out.println("Пожалуйста, введите корректные данные для этого продукта:");

                // Запрашиваем повторный ввод для этого продукта
                boolean validInput = false;
                while (!validInput) {
                    try {
                        String newInput = scanner.nextLine().trim();
                        String[] newParts = newInput.split("\\s*=\\s*");

                        if (newParts.length != 2) {
                            throw new IllegalArgumentException("Неверный формат ввода. Используйте: Название = Цена");
                        }

                        String name = newParts[0].trim();
                        double cost = Double.parseDouble(newParts[1].trim());

                        if (cost < 0) {
                            throw new IllegalArgumentException("Цена не может быть отрицательной");
                        }

                        productsMap.put(name, new Product(name, cost));
                        validInput = true;
                    } catch (Exception ex) {
                        System.out.println("Ошибка ввода: " + ex.getMessage());
                        System.out.println("Пожалуйста, попробуйте еще раз:");
                    }
                }
            }
        }

        // === Процесс совершения покупок ===
        System.out.println("Введите информацию о покупке в формате: Имя_Покупателя - Наименование_Покупки (для завершения введите 'END'): ");
        while (true) {
            String input = scanner.nextLine().trim();

            // Проверка на завершение ввода
            if (input.equalsIgnoreCase("END")) {
                break;
            }

            try {
                // Разбиваем строку на имя покупателя и название продукта
                String[] parts = input.split("\\s*-\\s*");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода");
                }

                String personName = parts[0].trim();
                String productName = parts[1].trim();

                // Получаем объекты покупателя и продукта
                Person person = peopleMap.get(personName);
                Product product = productsMap.get(productName);

                // Проверка существования покупателя и продукта
                if (person == null) {
                    System.out.println("Ошибка: Покупатель '" + personName + "' не найден");
                    continue;
                }
                if (product == null) {
                    System.out.println("Ошибка: Продукт '" + productName + "' не найден");
                    continue;
                }

                // Пытаемся совершить покупку и выводим результат
                if (person.buyProduct(product)) {
                    System.out.println(personName + " успешно купил(а) " + productName);
                } else {
                    System.out.println(personName + " не может позволить себе " + productName +
                            " (недостаточно средств: " + person.getMoney() + ")");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Используйте формат: Имя - Продукт");
            }
        }

        // Вывод итоговой информации
        System.out.println("\n=== Итоги покупок ===");
        for (Person person : peopleMap.values()) {
            System.out.println(person);
        }

        scanner.close();
    }
}