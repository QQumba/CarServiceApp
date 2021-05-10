package ua.nure.knysh.carservice.services;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.contract.CarDto;
import ua.nure.knysh.carservice.entities.Car;

import java.util.List;

@Service
public interface CarService extends CRUDService<CarDto>{
    List<CarDto> getFactoryCars(Long factoryId);
    List<CarDto> getPersonCars(Long personId);
    void addFactory(Long carId, Long factoryId);
}
