package ua.nure.knysh.carservice.services;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;

import java.util.List;

@Service
public interface CarService extends CRUDService<Car>{
    List<Car> getFactoryCars(Long factoryId);
    List<Car> getPersonCars(Long personId);
    void addFactory(Long carId, Long factoryId);
}
