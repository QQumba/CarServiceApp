package ua.nure.knysh.carservice.services.implementations;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.entities.Factory;
import ua.nure.knysh.carservice.entities.Person;
import ua.nure.knysh.carservice.repositories.CarRepository;
import ua.nure.knysh.carservice.repositories.FactoryRepository;
import ua.nure.knysh.carservice.repositories.PersonRepository;
import ua.nure.knysh.carservice.services.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CarDataService implements CarService {
    private final CarRepository carRepository;
    private final FactoryRepository factoryRepository;
    private final PersonRepository personRepository;

    public CarDataService(CarRepository carRepository, FactoryRepository factoryRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.factoryRepository = factoryRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Car> get(@NotNull Long id) {
        Optional<Car> car = carRepository.findById(id);
        if(car.isEmpty()){
            return Optional.empty();
        }
        return car;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Long> create(@NotNull Car car) {
        return Optional.of(carRepository.save(car).getId());
    }

    @Override
    public List<Car> getFactoryCars(Long factoryId) {
        Optional<Factory> factory = factoryRepository.findById(factoryId);
        if(factory.isEmpty()){
            return new ArrayList<>();
        }

        return factory.get().getCars();
    }

    @Override
    public List<Car> getPersonCars(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        if(person.isEmpty()){
            return new ArrayList<>();
        }

        return person.get().getCars();
    }

    @Override
    public void addFactory(Long carId, Long factoryId) {
        Optional<Car> car = carRepository.findById(carId);
        Optional<Factory> factory = factoryRepository.findById(factoryId);
        if(car.isEmpty() || factory.isEmpty()){
            throw new IllegalArgumentException("Car or factory with specified id does not exist.");
        }
        if(car.get().getFactories().contains(factory.get())){
            throw new IllegalArgumentException("Car already have this factory.");
        }

        car.get().getFactories().add(factory.get());
        carRepository.save(car.get());
    }

    @Override
    public boolean update(@NotNull Car car) {
        Optional<Car> carEntity = carRepository.findById(car.getId());
        if(carEntity.isEmpty()){
            return false;
        }
        carRepository.save(car);
        return true;
    }

    @Override
    public boolean delete(@NotNull Long id) {
        if(!carRepository.existsById(id)){
            return false;
        }

        carRepository.deleteById(id);
        return true;
    }
}
