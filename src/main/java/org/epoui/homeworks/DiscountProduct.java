package org.epoui.homeworks;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {
    private final double discount;
    private final LocalDate discountEndDate;

    public DiscountProduct(String name, double cost, double discount, LocalDate discountEndDate) {
        super(name, cost);

        // Валидация параметров скидки
        if (discount < 0) {
            throw new IllegalArgumentException("Скидка не может быть отрицательной");
        }
        if (discount > cost) {
            throw new IllegalArgumentException("Скидка не может быть больше стоимости продукта");
        }
        this.discount = discount;

        // Гарантируем, что дата не будет null
        this.discountEndDate = Objects.requireNonNull(discountEndDate, "Дата окончания скидки не может быть null");
    }

    @Override
    public double getCost() {
        // Если скидка активна (дата не прошла), применяем скидку
        if (LocalDate.now().isBefore(discountEndDate) || LocalDate.now().isEqual(discountEndDate)) {
            return super.getCost() - discount;
        }
        // Иначе возвращаем полную стоимость
        return super.getCost();
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDate getDiscountEndDate() {
        return discountEndDate;
    }

    public boolean isDiscountActive() {
        return !LocalDate.now().isAfter(discountEndDate);
    }

    @Override
    public String toString() {
        String base = super.toString();
        if (isDiscountActive()) {
            return base + " (скидка: " + discount + ", действует до: " + discountEndDate + ")";
        }
        return base + " (скидка истекла " + discountEndDate + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Double.compare(that.discount, discount) == 0 &&
                discountEndDate.equals(that.discountEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount, discountEndDate);
    }
}