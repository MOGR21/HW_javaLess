package org.epoui.attestation01;

import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private Product[] bag;
    private int bagSize;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.bag = new Product[1]; // Начальный размер пакета с продуктами
        this.bagSize = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3-х символов");
        }
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public Product[] getBag() {
        Product[] result = new Product[bagSize];
        System.arraycopy(bag, 0, result, 0, bagSize);
        return result;
    }

    public boolean buyProduct(Product product) {
        if (product.getCost() > money) {
            return false;
        }
        money -= product.getCost();
        if (bagSize >= bag.length) {
            Product[] newBag = new Product[bag.length * 2];
            System.arraycopy(bag, 0, newBag, 0, bag.length);
            bag = newBag;
        }
        bag[bagSize++] = product;
        return true;
    }

    @Override
    public String toString() {
        if (bagSize == 0) {
            return name + " - Ничего не куплено";
        }
        StringBuilder sb = new StringBuilder(name + " - ");
        for (int i = 0; i < bagSize; i++) {
            sb.append(bag[i].getName());
            if (i < bagSize - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
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
