package ua.nure.knysh.carservice.services;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService{
    Optional<Car> get(Long id);
    List<Car> getAll();
    Optional<Long> create(Car t);
    boolean update(Car t);
    boolean delete(Long id);
}
