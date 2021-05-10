package ua.nure.knysh.carservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.knysh.carservice.contract.CarDto;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.entities.Person;
import ua.nure.knysh.carservice.services.CarService;
import ua.nure.knysh.carservice.services.PersonService;

import java.util.List;

@Controller
@RequestMapping("/")
public class GreetingController {
    private final CarService carService;
    private final PersonService personService;

    public GreetingController(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @GetMapping
    public String index(Model model){
        List<CarDto> cars = carService.getAll();
        List<Person> persons = personService.getAll();

        model.addAttribute("persons", persons);
        model.addAttribute("cars", cars);
        return "cars";
    }
}
