package ua.nure.knysh.carservice.services;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Person;

@Service
public interface PersonService extends CRUDService<Person> {
    void addCar(Long personId, Long carId);
}
