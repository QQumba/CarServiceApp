package ua.nure.knysh.carservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.knysh.carservice.contract.PersonCar;
import ua.nure.knysh.carservice.entities.Person;
import ua.nure.knysh.carservice.repositories.PersonRepository;
import ua.nure.knysh.carservice.services.PersonService;

import javax.persistence.NamedAttributeNode;
import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return ResponseEntity.of(personService.get(id));
    }

    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personService.getAll();
    }

    @PostMapping
    public ResponseEntity<Long> createPerson(@RequestBody Person personEntity){
        return ResponseEntity.of(personService.create(personEntity));
    }

    @PostMapping("/add-car")
    public void addCar(@RequestBody PersonCar personCar){
        personService.addCar(personCar.getPersonId(), personCar.getCarId());
    }

    @PutMapping
    public ResponseEntity updatePerson(@RequestBody Person person){
        if(personService.update(person)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id){
        if(personService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
