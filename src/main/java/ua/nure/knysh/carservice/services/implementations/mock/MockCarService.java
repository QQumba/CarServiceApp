package ua.nure.knysh.carservice.services.implementations.mock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.services.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Scope("singleton")
public class MockCarService implements CarService {
    private final ConcurrentMap<Long, Car> cars = new ConcurrentHashMap<>();
    private Long currentId = 0L;

    @Override
    public Optional<Car> get(Long id) {
        Car car = cars.get(id);
        if(car == null){
            return Optional.empty();
        }
        return Optional.of(cars.get(id));
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public Optional<Long> create(Car car) {
        car.setId(++currentId);
        cars.put(car.getId(), car);
        return Optional.of(car.getId());
    }

    @Override
    public boolean update(Car car) {
        if(!cars.containsKey(car.getId())){
            return false;
        }

        cars.replace(car.getId(), car);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!cars.containsKey(id)){
            return false;
        }

        cars.remove(id);
        return true;
    }
}
