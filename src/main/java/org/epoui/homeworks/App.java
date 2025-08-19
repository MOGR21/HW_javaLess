package org.epoui.homeworks;

import java.time.LocalDate;
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
            if (input.isEmpty()) continue;

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
        System.out.println("Введите продукты в формате: Название = Цена [Скидка=РазмерСкидки,ДействуетДо=ГГГГ-ММ-ДД]; ...");
        String[] productsInput = scanner.nextLine().split(";\\s*");
        Map<String, Product> productsMap = new HashMap<>();

        for (String input : productsInput) {
            input = input.trim();
            if (input.isEmpty()) continue;

            try {
                if (input.contains("[")) {
                    // Обработка скидочного продукта
                    int bracketStart = input.indexOf('[');
                    int bracketEnd = input.indexOf(']');

                    if (bracketStart == -1 || bracketEnd == -1) {
                        throw new IllegalArgumentException("Неверный формат скидки. Отсутствуют скобки");
                    }

                    String mainPart = input.substring(0, bracketStart).trim();
                    String discountPart = input.substring(bracketStart + 1, bracketEnd).trim();

                    // Обработка основной части
                    String[] mainParts = mainPart.split("=");
                    if (mainParts.length != 2) {
                        throw new IllegalArgumentException("Неверный формат продукта. Используйте: Название = Цена");
                    }

                    String name = mainParts[0].trim();
                    double cost = Double.parseDouble(mainParts[1].trim());

                    // Обработка параметров скидки
                    String[] discountParams = discountPart.split(",");
                    double discount = 0;
                    LocalDate endDate = null;

                    for (String param : discountParams) {
                        String[] keyValue = param.split("=");
                        if (keyValue.length != 2) {
                            continue;
                        }

                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();

                        if (key.equalsIgnoreCase("Скидка")) {
                            discount = Double.parseDouble(value);
                        } else if (key.equalsIgnoreCase("ДействуетДо")) {
                            endDate = LocalDate.parse(value);
                        }
                    }

                    if (endDate == null) {
                        throw new IllegalArgumentException("Не указана дата окончания скидки");
                    }

                    productsMap.put(name, new DiscountProduct(name, cost, discount, endDate));
                } else {
                    // Обработка обычного продукта
                    String[] parts = input.split("=");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Неверный формат ввода. Используйте: Название = Цена");
                    }

                    String name = parts[0].trim();
                    double cost = Double.parseDouble(parts[1].trim());

                    productsMap.put(name, new Product(name, cost));
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + input + " - " + e.getMessage());
                System.out.println("Пожалуйста, введите корректные данные для этого продукта:");

                boolean validInput = false;
                while (!validInput) {
                    try {
                        String newInput = scanner.nextLine().trim();

                        if (newInput.contains("[")) {
                            // Обработка скидочного продукта (аналогично основному блоку)
                            int bracketStart = newInput.indexOf('[');
                            int bracketEnd = newInput.indexOf(']');

                            if (bracketStart == -1 || bracketEnd == -1) {
                                throw new IllegalArgumentException("Неверный формат скидки. Отсутствуют скобки");
                            }

                            String mainPart = newInput.substring(0, bracketStart).trim();
                            String discountPart = newInput.substring(bracketStart + 1, bracketEnd).trim();

                            String[] mainParts = mainPart.split("=");
                            if (mainParts.length != 2) {
                                throw new IllegalArgumentException("Неверный формат продукта");
                            }

                            String name = mainParts[0].trim();
                            double cost = Double.parseDouble(mainParts[1].trim());

                            String[] discountParams = discountPart.split(",");
                            double discount = 0;
                            LocalDate endDate = null;

                            for (String param : discountParams) {
                                String[] keyValue = param.split("=");
                                if (keyValue.length != 2) continue;

                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();

                                if (key.equalsIgnoreCase("Скидка")) {
                                    discount = Double.parseDouble(value);
                                } else if (key.equalsIgnoreCase("ДействуетДо")) {
                                    endDate = LocalDate.parse(value);
                                }
                            }

                            if (endDate == null) {
                                throw new IllegalArgumentException("Не указана дата окончания скидки");
                            }

                            productsMap.put(name, new DiscountProduct(name, cost, discount, endDate));
                        } else {
                            // Обработка обычного продукта
                            String[] newParts = newInput.split("=");
                            if (newParts.length != 2) {
                                throw new IllegalArgumentException("Неверный формат ввода");
                            }

                            String name = newParts[0].trim();
                            double cost = Double.parseDouble(newParts[1].trim());

                            productsMap.put(name, new Product(name, cost));
                        }
                        validInput = true;
                    } catch (Exception ex) {
                        System.out.println("Ошибка ввода: " + ex.getMessage());
                        System.out.println("Пожалуйста, попробуйте еще раз:");
                    }
                }
            }
        }

        // Процесс совершения покупок
        System.out.println("Введите информацию о покупке в формате: Имя_Покупателя - Наименование_Покупки (для завершения введите 'END'): ");
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("END")) {
                break;
            }

            try {
                String[] parts = input.split("\\s*-\\s*");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода");
                }

                String personName = parts[0].trim();
                String productName = parts[1].trim();

                Person person = peopleMap.get(personName);
                Product product = productsMap.get(productName);

                if (person == null) {
                    System.out.println("Ошибка: Покупатель '" + personName + "' не найден");
                    continue;
                }
                if (product == null) {
                    System.out.println("Ошибка: Продукт '" + productName + "' не найден");
                    continue;
                }

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