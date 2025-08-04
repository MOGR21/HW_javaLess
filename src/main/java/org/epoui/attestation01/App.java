package org.epoui.attestation01;

import java.util.Scanner;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вводим покупателей и их средства
        System.out.println("Введите покупателей и их средства в формате: Имя = Сумма; Имя2 = Сумма2; и т.д.: ");
        String[] peopleInput = scanner.nextLine().split(";\\s*");
        Person[] people = new Person[peopleInput.length];

        for (int i = 0; i < peopleInput.length; i++) {
            String[] parts = peopleInput[i].split("\\s*=\\s*");
            try {
                String name = parts[0].trim();
                double money = Double.parseDouble(parts[1].trim());
                people[i] = new Person(name, money);
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + peopleInput[i]);
                i--; // Повторяем ввод для этого покупателя
            }
        }

        // Ввод продуктов
        System.out.println("Введите продукты в формате: Название = Цена; Название2 = Цена2 и т.д.: ");
        String[] productsInput = scanner.nextLine().split(";\\s*");
        Product[] products = new Product[productsInput.length];

        for (int i = 0; i < productsInput.length; i++) {
            String[] parts = productsInput[i].split("\\s*=\\s*");
            try {
                String name = parts[0].trim();
                double cost = Double.parseDouble(parts[1].trim());
                products[i] = new Product(name, cost);
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + productsInput[i]);
                i--; // Повторяем ввод для этого продукта
            }
        }

        // Процесс покупки
        for (Person person : people) {
            System.out.println("\n" + person.getName() + " выбирает продукты:");

            while (true) {
                System.out.println("Доступные продукты:");
                for (int i = 0; i < products.length; i++) {
                    System.out.println((i+1) + ". " + products[i] + "руб");
                }

                System.out.println("Введите номер продукта для покупки (или END для завершения покупок):");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("END")) {
                    break;
                }

                try {
                    int productIndex = Integer.parseInt(input) - 1;
                    if (productIndex < 0 || productIndex >= products.length) {
                        System.out.println("Неверный номер продукта");
                        continue;
                    }

                    Product product = products[productIndex];
                    if (person.buyProduct(product)) {
                        System.out.println(person.getName() + " купил " + product.getName());
                    } else {
                        System.out.println(person.getName() + " не может позволить себе " + product.getName());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Введите номер продукта или END");
                }
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты покупок:");
        for (Person person : people) {
            System.out.println(person);
        }

        scanner.close();
    }
}