package ua.nure.knysh.carservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.knysh.carservice.contract.CarFactory;
import ua.nure.knysh.carservice.entities.Factory;
import ua.nure.knysh.carservice.services.FactoryService;

import java.util.List;

@RestController
@RequestMapping("api/factory")
public class FactoryController {
    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factory> getFactory(@PathVariable("id") Long id){
        return ResponseEntity.of(factoryService.get(id));
    }

    @GetMapping("/all")
    public List<Factory> getAllFactories(){
        return factoryService.getAll();
    }

    @GetMapping("/car/{carId}")
    public List<Factory> getCarFactories(@PathVariable Long carId) {
        return factoryService.getCarFactories(carId);
    }

    @PostMapping
    public ResponseEntity<Long> createFactory(@RequestBody Factory factory){
        return ResponseEntity.of(factoryService.create(factory));
    }

    @PostMapping("/add-car")
    public void addCar(@RequestBody CarFactory carFactory){
        factoryService.addCar(carFactory.getFactoryId(), carFactory.getCarId());
    }

    @PutMapping
    public ResponseEntity updateFactory(@RequestBody Factory factory){
        if(factoryService.update(factory)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFactory(@PathVariable("id") Long id){
        if(factoryService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
