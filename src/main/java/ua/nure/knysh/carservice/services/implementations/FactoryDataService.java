package ua.nure.knysh.carservice.services.implementations;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.entities.Factory;
import ua.nure.knysh.carservice.repositories.CarRepository;
import ua.nure.knysh.carservice.repositories.FactoryRepository;
import ua.nure.knysh.carservice.services.FactoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FactoryDataService implements FactoryService {
    private final FactoryRepository factoryRepository;
    private final CarRepository carRepository;

    public FactoryDataService(FactoryRepository factoryRepository, CarRepository carRepository) {
        this.factoryRepository = factoryRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Factory> get(Long id) {
        return factoryRepository.findById(id);
    }

    @Override
    public List<Factory> getAll() {
        return factoryRepository.findAll();
    }

    @Override
    public List<Factory> getCarFactories(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        if(car.isEmpty()){
            return new ArrayList<>();
        }
        return car.get().getFactories();
    }

    @Override
    public Optional<Long> create(Factory factory) {
        return Optional.of(factoryRepository.save(factory).getId());
    }

    @Override
    public void addCar(Long factoryId, Long carId) {
        Optional<Factory> factory = factoryRepository.findById(factoryId);
        Optional<Car> car = carRepository.findById(carId);
        if(factory.isEmpty() || car.isEmpty()){
            throw new IllegalArgumentException("Car or factory with specified id does not exist.");
        }
        if(factory.get().getCars().contains(car.get())){
            throw new IllegalArgumentException("Factory already have this car.");
        }

        car.get().getFactories().add(factory.get());
        carRepository.save(car.get());
    }

    @Override
    public boolean update(Factory factory) {
        Optional<Factory> factoryEntity = factoryRepository.findById(factory.getId());
        if(factoryEntity.isEmpty()){
            return false;
        }
        factoryRepository.save(factory);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!factoryRepository.existsById(id)){
            return false;
        }

        factoryRepository.deleteById(id);
        return true;
    }


}
