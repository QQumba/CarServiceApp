package ua.nure.knysh.carservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.knysh.carservice.contract.CarFactory;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.services.CarService;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {
    private static final String INFO_RESPONSE_MESSAGE = "Mykyta Knysh PZPI-18-8, My First Spring Application. 2021 &copy";

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getInfo(){
        return INFO_RESPONSE_MESSAGE;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id){
        return ResponseEntity.of(carService.get(id));
    }

    @GetMapping("/all")
    public List<Car> getAllCars(){
        return carService.getAll();
    }

    @GetMapping("/factory/{id}")
    public List<Car> getFactoryCars(@PathVariable("id") Long factoryId){
        return carService.getFactoryCars(factoryId);
    }

    @GetMapping("/person/{id}")
    public List<Car> getPersonCars(@PathVariable("id") Long personId){
        return carService.getPersonCars(personId);
    }

    @PostMapping
    public ResponseEntity<Long> createCar(@RequestBody Car car){
        return ResponseEntity.of(carService.create(car));
    }

    @PostMapping("/add-factory")
    public void addFactory(@RequestBody CarFactory carFactory){
        carService.addFactory(carFactory.getCarId(), carFactory.getFactoryId());
    }

    @PutMapping
    public ResponseEntity updateCar(@RequestBody Car car){
        if(carService.update(car)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id){
        if(carService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
