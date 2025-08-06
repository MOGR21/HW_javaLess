package org.epoui.attestation01;

import java.util.ArrayList;
import java.util.Objects;
import java.util.*;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> bag;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.bag = new ArrayList<>();
    }
    // Геттеры и сеттеры
    public String getName() { return name; }
    public double getMoney() { return money; }

     public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3-х символов");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getBag() {
        return new ArrayList<>(bag);
    }

    public boolean buyProduct(Product product) {
        Objects.requireNonNull(product, "Продукт не может быть null");

        if (product.getCost() > money) {
            return false;
        }

        money -= product.getCost();
        bag.add(product);
        return true;
    }

    @Override
    public String toString() {
        if (bag.isEmpty()) {
            return name + " - Ничего не куплено";
        }
        return name + " - " + bag.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }
}
