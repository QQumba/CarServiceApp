package ua.nure.knysh.carservice.services.implementations;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.entities.Person;
import ua.nure.knysh.carservice.repositories.CarRepository;
import ua.nure.knysh.carservice.repositories.PersonRepository;
import ua.nure.knysh.carservice.services.PersonService;

import java.util.List;
import java.util.Optional;

//TODO: replace factory domain entity with contract entity
@Service
public class PersonDataService implements PersonService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    public PersonDataService(PersonRepository personRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Person> get(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Long> create(Person personEntity) {
        return Optional.of(personRepository.save(personEntity).getId());
    }
    @Override
    public void addCar(Long personId, Long carId) {
        Optional<Person> person = personRepository.findById(personId);
        Optional<Car> car = carRepository.findById(carId);
        if(car.isEmpty() || person.isEmpty()){
            throw new IllegalArgumentException("Car or person with specified id does not exist.");
        }
        if(car.get().getPersonId().equals(personId)){
            throw new IllegalArgumentException();
        }

        car.get().setPerson(person.get());
        carRepository.save(car.get());
    }

    @Override
    public boolean update(Person personEntity) {
        Optional<Person> person = personRepository.findById(personEntity.getId());
        if(person.isEmpty()){
            return false;
        }

        person.get()
                .setName(personEntity.getName())
                .setEmail(personEntity.getEmail());

        personRepository.save(person.get());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if(!personRepository.existsById(id)){
            return false;
        }

        personRepository.deleteById(id);
        return true;
    }
}
