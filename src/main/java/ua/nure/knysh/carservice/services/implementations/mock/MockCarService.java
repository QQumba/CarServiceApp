package ua.nure.knysh.carservice.services.implementations.mock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.contract.CarDto;
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
    private final ConcurrentMap<Long, CarDto> cars = new ConcurrentHashMap<>();
    private Long currentId = 0L;

    @Override
    public Optional<CarDto> get(Long id) {
        var car = cars.get(id);
        if(car == null){
            return Optional.empty();
        }
        return Optional.of(cars.get(id));
    }

    @Override
    public List<CarDto> getAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public Optional<Long> create(CarDto carDto) {
        carDto.setId(++currentId);
        cars.put(carDto.getId(), carDto);
        return Optional.of(carDto.getId());
    }

    @Override
    public boolean update(CarDto carDto) {
        if(!cars.containsKey(carDto.getId())){
            return false;
        }

        cars.replace(carDto.getId(), carDto);
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

    @Override
    public List<CarDto> getFactoryCars(Long factoryId) {
        return null;
    }

    @Override
    public List<CarDto> getPersonCars(Long personId) {
        return null;
    }

    @Override
    public void addFactory(Long carId, Long factoryId) {

    }
}
