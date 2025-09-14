package repository;

import model.Car;
import java.util.List;
import java.util.Optional;

public interface CarsRepository {
    List<Car> loadCarsFromFile(String filename);
    void saveCarsToFile(String filename, List<Car> cars);
    List<String> findNumbersByColorOrMileage(List<Car> cars, String colorToFind, long mileageToFind);
    long countUniqueModelsInPriceRange(List<Car> cars, long minPrice, long maxPrice);
    Optional<String> findColorOfCheapestCar(List<Car> cars);
    double calculateAverageCostByModel(List<Car> cars, String modelToFind);
}
