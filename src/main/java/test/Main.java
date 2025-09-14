package test;

import model.Car;
import repository.CarsRepository;
import repository.CarsRepositoryImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarsRepository repository = new CarsRepositoryImpl();

        System.out.println("=== ЗАГРУЗКА ДАННЫХ ===");
        List<Car> cars = repository.loadCarsFromFile("data/cars.txt");

        System.out.println("\n=== АВТОМОБИЛИ В БАЗЕ ===");
        System.out.printf("%-8s %-8s %-8s %-9s %s%n",
                "Номер", "Модель", "Цвет", "Пробег", "Стоимость");
        for (Car car : cars) {
            System.out.println(car);
        }

        // Ввод условий поиска
        String colorToFind = "Black";
        long mileageToFind = 0L;
        long minPrice = 700_000L;
        long maxPrice = 800_000L;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        List<String> numbers = repository.findNumbersByColorOrMileage(cars, colorToFind, mileageToFind);
        System.out.println("\nНомера автомобилей по цвету или пробегу: " + String.join(" ", numbers));

        long uniqueCount = repository.countUniqueModelsInPriceRange(cars, minPrice, maxPrice);
        System.out.println("Уникальные автомобили: " + uniqueCount + " шт.");

        repository.findColorOfCheapestCar(cars).ifPresentOrElse(
                color -> System.out.println("Цвет автомобиля с минимальной стоимостью: " + color),
                () -> System.out.println("Автомобили не найдены")
        );

        double avgToyota = repository.calculateAverageCostByModel(cars, modelToFind1);
        double avgVolvo = repository.calculateAverageCostByModel(cars, modelToFind2);

        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind1, avgToyota);
        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind2, avgVolvo);
    }
}