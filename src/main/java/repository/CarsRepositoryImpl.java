package repository;

import model.Car;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {

    @Override
    public List<Car> loadCarsFromFile(String filename) {
        List<Car> cars = new ArrayList<>();
        File file = new File(filename);
        System.out.println("Попытка загрузить файл: " + file.getAbsolutePath());
        System.out.println("Файл существует: " + file.exists());
        System.out.println("Размер файла: " + file.length() + " байт");

        if (!file.exists()) {
            System.out.println("Файл не найден");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("[НОМЕР") || line.startsWith("Number")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        String number = parts[0].trim();
                        String model = parts[1].trim();
                        String color = parts[2].trim();
                        long mileage = Long.parseLong(parts[3].trim());
                        long cost = Long.parseLong(parts[4].trim());
                        cars.add(new Car(number, model, color, mileage, cost));
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка формата чисел в строке " + lineCount + ": " + line);
                    }
                }
            }
            System.out.println("Загружено " + cars.size() + " автомобилей из файла");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return cars;
    }

    @Override
    public void saveCarsToFile(String filename, List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Number Model Color Mileage Cost");
            for (Car car : cars) {
                writer.printf("%s|%s|%s|%d|%d%n",
                        car.getNumber(), car.getModel(), car.getColor(),
                        car.getMileage(), car.getCost());
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public List<String> findNumbersByColorOrMileage(List<Car> cars, String colorToFind, long mileageToFind) {
        return cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public long countUniqueModelsInPriceRange(List<Car> cars, long minPrice, long maxPrice) {
        return cars.stream()
                .filter(car -> car.getCost() >= minPrice && car.getCost() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
    }

    @Override
    public Optional<String> findColorOfCheapestCar(List<Car> cars) {
        return cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor);
    }

    @Override
    public double calculateAverageCostByModel(List<Car> cars, String modelToFind) {
        return cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
    }
}