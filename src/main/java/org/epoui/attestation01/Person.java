package org.epoui.attestation01;

public class Person {
    private String name;
    private double money;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("Имя не может быть короче 2-х символов");
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
}
